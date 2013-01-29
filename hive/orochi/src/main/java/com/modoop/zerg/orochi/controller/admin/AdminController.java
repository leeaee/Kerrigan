package com.modoop.zerg.orochi.controller.admin;

import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.exception.EntityAlreadyExistException;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.orochi.service.AdminService;
import com.modoop.zerg.taipan.core.jersey.JerseyException;
import com.modoop.zerg.taipan.core.mapper.JsonMapper;
import com.modoop.zerg.taipan.core.util.Servlets;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController
{
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    private AdminService adminService;

    /**
     * 管理员浏览
     */
    @RequiresPermissions("admin:read")
    @RequestMapping(value = {"", "browse"}, method = RequestMethod.GET)
    public String browse(Model model, ServletRequest request)
    {
        Map<String, Object> parameters = Servlets.getParametersStartingWith(request, "search_");
        logger.debug("search parameters: {}", JsonMapper.buildNonDefaultMapper().toJson(parameters));
        model.addAttribute("params", parameters);
        model.addAttribute("page", adminService.searchAdmins(parameters));
        return "admin/admin_browse";
    }

    @RequiresPermissions("admin:read")
    @RequestMapping(value = "detail/{name}", method = RequestMethod.GET)
    public String detail(@PathVariable("name") String name, Model model) throws EntityNotFoundException
    {
        model.addAttribute("admin", adminService.readAdmin(name));
        return "admin/admin_detail";
    }

    /**
     * 管理员创建
     */
    @RequiresPermissions("admin:change")
    @RequestMapping(value = "create/form", method = RequestMethod.GET)
    public String createForm(Model model)
    {
        model.addAttribute("roles", adminService.getAdminRoles());
        return "admin/admin_create";
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute Admin admin, @RequestParam(value = "roleIds") List<Long> roleIds, RedirectAttributes redirectAttributes)
            throws EntityAlreadyExistException, EntityNotFoundException
    {
        for (Long id : roleIds)
        {
            Role role = new Role(id);
            admin.getRoles().add(role);
        }
        logger.debug("Create admin: {}", admin);
        adminService.createAdmin(admin);

        redirectAttributes.addFlashAttribute("message", "Save administrator successfully.");
        return "redirect:/admin/browse";
    }

    /**
     * 管理员编辑
     */
    @RequiresPermissions("admin:change")
    @RequestMapping(value = "update/{name}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("name") String name, Model model) throws EntityNotFoundException
    {
        model.addAttribute("roles", adminService.getAdminRoles());
        model.addAttribute("admin", adminService.readAdmin(name));
        return "admin/admin_update";
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute Admin admin, @RequestParam(value = "roleIds") List<Long> roleIds, RedirectAttributes redirectAttributes)
            throws EntityAlreadyExistException, EntityNotFoundException
    {
        admin.getRoles().clear();
        for (Long roleId : roleIds)
        {
            Role role = new Role(roleId);
            admin.getRoles().add(role);
        }

        adminService.updateAdmin(admin);

        redirectAttributes.addFlashAttribute("message", "Update administrator successfully.");
        return "redirect:/admin/browse";
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(value = "delete/{name}", method = RequestMethod.GET)
    public String delete(@PathVariable("name") String name, RedirectAttributes redirectAttributes, Principal principal)
    {
        logger.debug("Delete admin: {}", name);
        if (principal.getName().equals(name))
        {
            redirectAttributes.addFlashAttribute("error", "Could not delete administrator self.");
            return "redirect:/admin/browse";
        }
        try
        {
            adminService.deleteAdmin(name);
        }
        catch (JerseyException e)
        {
            redirectAttributes.addFlashAttribute("error", "Could not delete administrator: " + e.getMessage());
            return "redirect:/admin/browse";
        }

        redirectAttributes.addFlashAttribute("message", "Delete administrator successfully.");
        return "redirect:/admin/browse";
    }

    @Autowired
    public void setAdminService(AdminService adminService)
    {
        this.adminService = adminService;
    }
}

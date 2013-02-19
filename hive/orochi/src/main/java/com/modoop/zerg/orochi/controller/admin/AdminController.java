package com.modoop.zerg.orochi.controller.admin;

import com.modoop.zerg.orochi.entity.admin.Admin;
import com.modoop.zerg.orochi.entity.admin.Role;
import com.modoop.zerg.orochi.exception.EntityAlreadyExistException;
import com.modoop.zerg.orochi.exception.EntityNotFoundException;
import com.modoop.zerg.orochi.service.AdminService;
import com.modoop.zerg.orochi.validator.checks.OrderedChecks;
import com.modoop.zerg.taipan.core.entity.ajax.AjaxResponse;
import com.modoop.zerg.taipan.core.i18n.I18NMessage;
import com.modoop.zerg.taipan.core.util.Servlets;
import com.modoop.zerg.taipan.core.validator.BeanValidators;
import com.modoop.zerg.taipan.core.web.controller.AbstractController;
import com.modoop.zerg.taipan.core.web.exception.WebException;
import com.modoop.zerg.taipan.core.web.view.Button;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Validator;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController
{
    private AdminService adminService;

    private Validator validator;

    /**
     * 管理员浏览
     */
    @RequiresPermissions("admin:read")
    @RequestMapping(value = {"", "browse"}, method = RequestMethod.GET)
    public String browse(Model model, ServletRequest request)
    {
        Map<String, Object> parameters = Servlets.getParametersStartingWith(request, null);
        model.addAttribute("roles", adminService.getAdminRoles());
        model.addAttribute("params", parameters);
        model.addAttribute("page", adminService.searchAdmins(parameters));

        return "admin/admin_browse";
    }

    @RequiresPermissions("admin:read")
    @RequestMapping(value = "detail/{name}", method = RequestMethod.GET)
    public String detail(@PathVariable("name") String name, Model model) throws EntityNotFoundException
    {
        logger.debug("View admin: {}", name);
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
    {
        for (Long id : roleIds)
        {
            Role role = new Role(id);
            admin.getRoles().add(role);
        }

        adminService.createAdmin(admin);
        logger.debug("Create admin: {}", admin);

        I18NMessage message = new I18NMessage("msg.ok", new I18NMessage("msg.admin.create", admin.getName()));
        String action = "location.href = '" + context.getContextPath() + "/admin/detail/" + admin.getName() + "'";
        Button button = new Button(new I18NMessage("act.admin.details"), action, "btn btn-success");
        handleMessage(redirectAttributes, message, button);

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
    {
        admin.getRoles().clear();
        for (Long roleId : roleIds)
        {
            Role role = new Role(roleId);
            admin.getRoles().add(role);
        }

        adminService.updateAdmin(admin);
        logger.debug("Update admin: {}", admin);

        I18NMessage message = new I18NMessage("msg.ok", new I18NMessage("msg.admin.update", admin.getName()));
        String action = "location.href = '" + context.getContextPath() + "/admin/detail/" + admin.getName() + "'";
        Button button = new Button(new I18NMessage("act.admin.details"), action, "btn btn-success");
        handleMessage(redirectAttributes, message, button);

        return "redirect:/admin/browse";
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "name") List<String> names, RedirectAttributes redirectAttributes, Principal principal)
    {
        logger.debug("Delete admins: {}", names);
        for (String name : names)
        {
            if (principal.getName().equals(name))
            {
                redirectAttributes.addFlashAttribute("error", "Could not delete administrator self.");
                return "redirect:/admin/browse";
            }
            try
            {
                adminService.deleteAdmin(name);
            }
            catch (WebException e)
            {
                redirectAttributes.addFlashAttribute("error", "Could not delete administrator: " + e.getMessage());
                return "redirect:/admin/browse";
            }
        }

        I18NMessage message = new I18NMessage("msg.ok", new I18NMessage("msg.admin.delete", names.size()));
        handleMessage(redirectAttributes, message);

        return "redirect:/admin/browse";
    }


    @RequestMapping(value = "validate", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String validate(@ModelAttribute Admin admin) throws EntityAlreadyExistException
    {
        logger.debug("Validate admin {}", admin);
        BeanValidators.validateWithException(validator, admin, OrderedChecks.class);
        boolean success = adminService.checkAdminNotExist(admin.getName());
        AjaxResponse response = new AjaxResponse();
        response.setSuccess(success);
        return jsonMapper.toJson(response);
    }


    /**
     * 不自动绑定对象中的roleIds属性，另行处理。
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setDisallowedFields("roleIds");
    }

    @Autowired
    public void setAdminService(AdminService adminService)
    {
        this.adminService = adminService;
    }

    @Autowired
    public void setValidator(Validator validator)
    {
        this.validator = validator;
    }
}

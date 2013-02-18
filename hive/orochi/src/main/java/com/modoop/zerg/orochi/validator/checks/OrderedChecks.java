package com.modoop.zerg.orochi.validator.checks;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @Author: Genkyo Lee
 */
@GroupSequence({Default.class, FeatureChecks.class})
public interface OrderedChecks
{
}

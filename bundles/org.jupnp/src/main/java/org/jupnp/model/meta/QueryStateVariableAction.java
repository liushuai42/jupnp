/*
 * Copyright (C) 2011-2025 4th Line GmbH, Switzerland and others
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License Version 1 or later
 * ("CDDL") (collectively, the "License"). You may not use this file
 * except in compliance with the License. See LICENSE.txt for more
 * information.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * SPDX-License-Identifier: CDDL-1.0
 */
package org.jupnp.model.meta;

import java.util.List;

import org.jupnp.model.ValidationError;

/**
 * Describes a single action, the deprecated "query any state variable" action.
 *
 * Note: This is already deprecated in UDA 1.0!
 *
 * @author Christian Bauer
 */
public class QueryStateVariableAction<S extends Service> extends Action<S> {

    public static final String INPUT_ARG_VAR_NAME = "varName";
    public static final String OUTPUT_ARG_RETURN = "return";

    public static final String ACTION_NAME = "QueryStateVariable";
    public static final String VIRTUAL_STATEVARIABLE_INPUT = "VirtualQueryActionInput";
    public static final String VIRTUAL_STATEVARIABLE_OUTPUT = "VirtualQueryActionOutput";

    public QueryStateVariableAction() {
        this(null);
    }

    public QueryStateVariableAction(S service) {
        super(ACTION_NAME, new ActionArgument[] {
                new ActionArgument(INPUT_ARG_VAR_NAME, VIRTUAL_STATEVARIABLE_INPUT, ActionArgument.Direction.IN),
                new ActionArgument(OUTPUT_ARG_RETURN, VIRTUAL_STATEVARIABLE_OUTPUT, ActionArgument.Direction.OUT), });
        setService(service);
    }

    @Override
    public String getName() {
        return ACTION_NAME;
    }

    @Override
    public List<ValidationError> validate() {
        return List.of();
    }
}

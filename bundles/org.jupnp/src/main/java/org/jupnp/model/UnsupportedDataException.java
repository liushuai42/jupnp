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
package org.jupnp.model;

/**
 * Thrown by processors/converters when errors occurred.
 * <p>
 * This exception indicates that received data was in an invalid format and/or could
 * not be parsed or converted. You typically can recover from this failure after
 * catching (and logging?) the exception.
 * </p>
 *
 * @author Christian Bauer
 */
public class UnsupportedDataException extends RuntimeException {

    private static final long serialVersionUID = 661795454401413339L;

    protected Object data;

    public UnsupportedDataException(String s) {
        super(s);
    }

    public UnsupportedDataException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UnsupportedDataException(String s, Throwable throwable, Object data) {
        super(s, throwable);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

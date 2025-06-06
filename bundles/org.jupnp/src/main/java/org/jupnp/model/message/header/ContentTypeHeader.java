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
package org.jupnp.model.message.header;

import org.jupnp.util.MimeType;

/**
 * @author Christian Bauer
 */
public class ContentTypeHeader extends UpnpHeader<MimeType> {

    public static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/xml");
    public static final MimeType DEFAULT_CONTENT_TYPE_UTF8 = MimeType.valueOf("text/xml;charset=\"utf-8\"");

    public ContentTypeHeader() {
        setValue(DEFAULT_CONTENT_TYPE);
    }

    public ContentTypeHeader(MimeType contentType) {
        setValue(contentType);
    }

    public ContentTypeHeader(String s) throws InvalidHeaderException {
        setString(s);
    }

    @Override
    public void setString(String s) throws InvalidHeaderException {
        setValue(MimeType.valueOf(s));
    }

    @Override
    public String getString() {
        return getValue().toString();
    }

    public boolean isUDACompliantXML() {
        // UDA spec says "must be text/xml", however, sometimes you get a charset token as well...
        return isText() && getValue().getSubtype().equals(DEFAULT_CONTENT_TYPE.getSubtype());
    }

    public boolean isText() {
        return getValue() != null && getValue().getType().equals(DEFAULT_CONTENT_TYPE.getType());
    }
}

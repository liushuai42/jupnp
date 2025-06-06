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
package org.jupnp.gena;

import org.jupnp.binding.annotations.AnnotationLocalServiceBinder;
import org.jupnp.binding.annotations.UpnpAction;
import org.jupnp.binding.annotations.UpnpInputArgument;
import org.jupnp.binding.annotations.UpnpOutputArgument;
import org.jupnp.binding.annotations.UpnpServiceId;
import org.jupnp.binding.annotations.UpnpServiceType;
import org.jupnp.binding.annotations.UpnpStateVariable;
import org.jupnp.data.SampleData;
import org.jupnp.model.meta.DeviceDetails;
import org.jupnp.model.meta.LocalDevice;
import org.jupnp.model.meta.LocalService;
import org.jupnp.model.types.UDADeviceType;

/**
 * @author Christian Bauer
 */
public class GenaSampleData {

    public static LocalDevice createTestDevice() throws Exception {
        return createTestDevice(LocalTestService.class);
    }

    public static LocalDevice createTestDevice(Class<?> clazz) throws Exception {
        return createTestDevice(SampleData.readService(new AnnotationLocalServiceBinder(), clazz));
    }

    public static LocalDevice createTestDevice(LocalService service) throws Exception {
        return new LocalDevice(SampleData.createLocalDeviceIdentity(), new UDADeviceType("BinaryLight", 1),
                new DeviceDetails("Example Binary Light"), service);
    }

    @org.jupnp.binding.annotations.UpnpService(serviceId = @UpnpServiceId("SwitchPower"), serviceType = @UpnpServiceType(value = "SwitchPower", version = 1))
    public static class LocalTestService {

        @UpnpStateVariable(sendEvents = false)
        private boolean target = false;

        @UpnpStateVariable
        private boolean status = false;

        @UpnpStateVariable(defaultValue = "foo")
        Foo someVar;

        @UpnpAction
        public void setTarget(@UpnpInputArgument(name = "NewTargetValue") boolean newTargetValue) {
            target = newTargetValue;
            status = newTargetValue;
        }

        @UpnpAction(out = @UpnpOutputArgument(name = "RetTargetValue"))
        public boolean getTarget() {
            return target;
        }

        @UpnpAction(name = "GetStatus", out = @UpnpOutputArgument(name = "ResultStatus", getterName = "getStatus"))
        public void dummyStatus() {
            // NOOP
        }

        public boolean getStatus() {
            return status;
        }
    }

    public enum Foo {
        foo,
        bar
    }
}

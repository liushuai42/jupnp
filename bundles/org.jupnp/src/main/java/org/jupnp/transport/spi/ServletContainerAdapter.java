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
package org.jupnp.transport.spi;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import javax.servlet.Servlet;

/**
 * Implement this to provide your own servlet container (instance),
 * <p>
 * It's OK if you don't start or stop your container when this adapter is
 * called. You can treat the {@link #startIfNotRunning()} and
 * {@link #stopIfRunning()} methods as suggestions, they only indicate what
 * the UPnP stack wants to do. If your servlet container handles other
 * services, keep it running all the time.
 * </p>
 * <p>
 * An implementation must be thread-safe, all methods might be called concurrently
 * by several threads.
 * </p>
 *
 * @author Christian Bauer
 */
public interface ServletContainerAdapter {

    /**
     * Might be called several times to integrate the servlet container with jUPnP's executor
     * configuration. You can ignore this call if you want to configure the container's thread
     * pooling independently from jUPnP. If you use the given jUPnP <code>ExecutorService</code>,
     * make sure the Jetty container won't shut it down when {@link #stopIfRunning()} is called!
     *
     * @param executorService The service to use when spawning new servlet execution threads.
     */
    void setExecutorService(ExecutorService executorService);

    /**
     * Might be called several times to set up the connectors. This is the host/address
     * and the port jUPnP expects to receive HTTP requests on. If you set up your HTTP
     * server connectors elsewhere and ignore when jUPnP calls this method, make sure
     * you configure jUPnP with the correct host/port of your servlet container.
     *
     * @param host The host address for the socket.
     * @param port The port, might be <code>-1</code> to bind to an ephemeral port.
     * @return The actual registered local port.
     * @throws IOException If the connector couldn't be opened to retrieve the registered local port.
     */
    int addConnector(String host, int port) throws IOException;

    /**
     * Might be called several times to register (the same) handler for UPnP
     * requests, should only register it once.
     *
     * @param contextPath The context path prefix for all UPnP requests.
     * @param servlet The servlet handling all UPnP requests.
     */
    void registerServlet(String contextPath, Servlet servlet);

    /**
     * Start your servlet container if it isn't already running, might be called multiple times.
     */
    void startIfNotRunning();

    /**
     * Stop your servlet container if it's still running, might be called multiple times.
     */
    void stopIfRunning();
}

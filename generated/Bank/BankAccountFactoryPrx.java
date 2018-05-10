// **********************************************************************
//
// Copyright (c) 2003-2018 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.1
//
// <auto-generated>
//
// Generated from file `bank.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Bank;

public interface BankAccountFactoryPrx extends com.zeroc.Ice.ObjectPrx
{
    default BankAccountPrx createAccount(Person person)
    {
        return createAccount(person, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default BankAccountPrx createAccount(Person person, java.util.Map<String, String> context)
    {
        return _iceI_createAccountAsync(person, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<BankAccountPrx> createAccountAsync(Person person)
    {
        return _iceI_createAccountAsync(person, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<BankAccountPrx> createAccountAsync(Person person, java.util.Map<String, String> context)
    {
        return _iceI_createAccountAsync(person, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<BankAccountPrx> _iceI_createAccountAsync(Person iceP_person, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<BankAccountPrx> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "createAccount", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     Person.ice_write(ostr, iceP_person);
                 }, istr -> {
                     BankAccountPrx ret;
                     ret = BankAccountPrx.uncheckedCast(istr.readProxy());
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static BankAccountFactoryPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), BankAccountFactoryPrx.class, _BankAccountFactoryPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static BankAccountFactoryPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), BankAccountFactoryPrx.class, _BankAccountFactoryPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static BankAccountFactoryPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), BankAccountFactoryPrx.class, _BankAccountFactoryPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static BankAccountFactoryPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), BankAccountFactoryPrx.class, _BankAccountFactoryPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static BankAccountFactoryPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, BankAccountFactoryPrx.class, _BankAccountFactoryPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static BankAccountFactoryPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, BankAccountFactoryPrx.class, _BankAccountFactoryPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default BankAccountFactoryPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (BankAccountFactoryPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default BankAccountFactoryPrx ice_adapterId(String newAdapterId)
    {
        return (BankAccountFactoryPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default BankAccountFactoryPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (BankAccountFactoryPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default BankAccountFactoryPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (BankAccountFactoryPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default BankAccountFactoryPrx ice_invocationTimeout(int newTimeout)
    {
        return (BankAccountFactoryPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default BankAccountFactoryPrx ice_connectionCached(boolean newCache)
    {
        return (BankAccountFactoryPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default BankAccountFactoryPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (BankAccountFactoryPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default BankAccountFactoryPrx ice_secure(boolean b)
    {
        return (BankAccountFactoryPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default BankAccountFactoryPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (BankAccountFactoryPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default BankAccountFactoryPrx ice_preferSecure(boolean b)
    {
        return (BankAccountFactoryPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default BankAccountFactoryPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (BankAccountFactoryPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default BankAccountFactoryPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (BankAccountFactoryPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default BankAccountFactoryPrx ice_collocationOptimized(boolean b)
    {
        return (BankAccountFactoryPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default BankAccountFactoryPrx ice_twoway()
    {
        return (BankAccountFactoryPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default BankAccountFactoryPrx ice_oneway()
    {
        return (BankAccountFactoryPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default BankAccountFactoryPrx ice_batchOneway()
    {
        return (BankAccountFactoryPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default BankAccountFactoryPrx ice_datagram()
    {
        return (BankAccountFactoryPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default BankAccountFactoryPrx ice_batchDatagram()
    {
        return (BankAccountFactoryPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default BankAccountFactoryPrx ice_compress(boolean co)
    {
        return (BankAccountFactoryPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default BankAccountFactoryPrx ice_timeout(int t)
    {
        return (BankAccountFactoryPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default BankAccountFactoryPrx ice_connectionId(String connectionId)
    {
        return (BankAccountFactoryPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default BankAccountFactoryPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (BankAccountFactoryPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::Bank::BankAccountFactory";
    }
}

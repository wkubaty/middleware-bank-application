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

public interface PremiumBankAccount extends BankAccount
{
    String getLoanInfo(String GUID, long amount, int months, CurrencyCode currencyCode, com.zeroc.Ice.Current current)
        throws WrongGUIDException;

    static final String[] _iceIds =
    {
        "::Bank::BankAccount",
        "::Bank::PremiumBankAccount",
        "::Ice::Object"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Bank::PremiumBankAccount";
    }

    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getLoanInfo(PremiumBankAccount obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_GUID;
        long iceP_amount;
        int iceP_months;
        CurrencyCode iceP_currencyCode;
        iceP_GUID = istr.readString();
        iceP_amount = istr.readLong();
        iceP_months = istr.readInt();
        iceP_currencyCode = CurrencyCode.ice_read(istr);
        inS.endReadParams();
        String ret = obj.getLoanInfo(iceP_GUID, iceP_amount, iceP_months, iceP_currencyCode, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    final static String[] _iceOps =
    {
        "getBalanceInfo",
        "getLoanInfo",
        "getOwnerInfo",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return BankAccount._iceD_getBalanceInfo(this, in, current);
            }
            case 1:
            {
                return _iceD_getLoanInfo(this, in, current);
            }
            case 2:
            {
                return BankAccount._iceD_getOwnerInfo(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 5:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 6:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
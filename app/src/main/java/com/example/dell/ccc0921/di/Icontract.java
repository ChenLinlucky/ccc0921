package com.example.dell.ccc0921.di;

public interface Icontract {
    interface iview{
        void data(String s);
    }
    interface ipresenter<iview>{
        void attchview(iview iview);
        void datachview(iview iview);
        void requestinfo();
    }
    interface imoudle{
        interface callisten{
            void requestmsg(String s);
        }
        void requestdata(callisten callisten);
    }
}

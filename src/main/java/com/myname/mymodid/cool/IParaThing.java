package com.myname.mymodid.cool;

public interface IParaThing {
    //TODO: ID should not be string, we should have our own interface for it since strings are final.
    String paraID();

    String localizedName();
}

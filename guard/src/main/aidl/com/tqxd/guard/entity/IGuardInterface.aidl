// IGuardInterface.aidl
package com.tqxd.guard.entity;

// Declare any non-default types here with import statements
import com.tqxd.guard.entity.GuardConfig;

interface IGuardInterface {
    void wakeup(in GuardConfig config);
    void connectionTimes(in int time);
}
package com.six.qb.permission;

public interface PermissionListener {

    public void  onGranted();

    public void  onDenied();

    public void onShowRationale(String[] permissions);
}


package com.six.qiangbao.permission;

public interface PermissionListener {

    public void  onGranted();

    public void  onDenied();

    public void onShowRationale(String[] permissions);
}


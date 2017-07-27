package com.rockwellstudios.rockwellshop.core.listeners;

/**
 * Created by andrew on 27.07.17.
 */

public interface onDatabaseOperationCompleteListener {

    void onDatabaseOperationFailed(String error);

    void onDatabaseOperationSucceed(String message);


}

package com.app.jcs.utils

import android.os.Parcelable
import com.app.jcs.api.apimodels.ParentLogin
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IntentUtil(
    var parentLogin: ParentLogin
) : Parcelable

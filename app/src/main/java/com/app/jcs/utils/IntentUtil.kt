package com.app.jcs.utils

import android.os.Parcelable
import com.app.jcs.api.apimodels.ParentLogin
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IntentUtilParent(
    var parentLogin: ParentLogin
) : Parcelable


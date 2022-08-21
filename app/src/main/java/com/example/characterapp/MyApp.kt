package com.example.characterapp

import android.app.Application
import android.os.Parcel
import android.os.Parcelable

class MyApp() : Application(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyApp> {
        override fun createFromParcel(parcel: Parcel): MyApp {
            return MyApp(parcel)
        }

        override fun newArray(size: Int): Array<MyApp?> {
            return arrayOfNulls(size)
        }
    }
}
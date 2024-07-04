package com.jornaler.fragment

import com.jornaler.R
import com.journaler.fragment.BaseFragment

class ManualFragment() : BaseFragment() {
    override val logTag: String = "Manual Fragment"
    override fun getLayout(): Int = R.layout.fragment_manual
}
package com.highestaim.workers.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.highestaim.workers.R
import com.highestaim.workers.model.WorkersModel
import com.highestaim.workers.util.Validator
import com.highestaim.workers.util.forceCloseKeyboard
import com.highestaim.workers.util.showKeyboard
import com.highestaim.workers.util.toast
import com.highestaim.workers.viewmodel.WorkersUpdateViewModel
import kotlinx.android.synthetic.main.worker_detail_fragment.*

class WorkersDetailFragment : BaseFragment() {

    private lateinit var workersUpdateViewModel: WorkersUpdateViewModel
    private val workerInfo by lazy {  arguments?.get("worker") as? WorkersModel.Data }

    override fun getLayoutId() = R.layout.worker_detail_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        workersUpdateViewModel = ViewModelProvider(this).get(WorkersUpdateViewModel::class.java)
        initUserInfo()
        setOnEditNameClickListener()
        setOnEditSalaryClickListener()
        setOnEditAgeClickListener()

        enableToolbarBackButton()

        setToolbarBackButtonClickListener()

        workerInfo?.name?.let { setToolbarTitle(it) }
    }

    private fun initUserInfo() {
        nameEditText?.setText(workerInfo?.name)
        salaryEditText?.setText(workerInfo?.salary)
        ageEditText?.setText(workerInfo?.age)
    }


    private fun setOnEditNameClickListener() {
        nameEditImageView?.setOnClickListener {
            nameEditText?.length()?.let { nameEditText?.setSelection(it) }

            if (checkState(nameEditText,nameEditImageView)) {
                if (Validator.isValidUserName(nameEditText?.text.toString().trim())) {
                    workerInfo?.id?.let { changeName(nameEditText?.text.toString().trim(), it) }
                } else {
                    activity?.toast(R.string.please_enter_name)
                }
            }
        }
    }

    private fun setOnEditSalaryClickListener() {
        salaryEditImageView?.setOnClickListener {
            salaryEditText?.length()?.let { salaryEditText?.setSelection(it) }

            if (checkState(salaryEditText,salaryEditImageView)) {
                if (Validator.isValidSalary(salaryEditText?.text.toString().trim())) {
                    workerInfo?.id?.let { changeSalary(salaryEditText?.text.toString().trim(), it) }
                } else {
                    activity?.toast(R.string.please_enter_correct_salary)
                }
            }
        }
    }

    private fun setOnEditAgeClickListener() {
        ageEditImageView?.setOnClickListener {
            ageEditText?.length()?.let { ageEditText?.setSelection(it) }

            if (checkState(ageEditText,ageEditImageView)) {
                if (Validator.isValidAge(ageEditText?.text.toString().trim())) {
                    workerInfo?.id?.let { changeAge(ageEditText?.text.toString().trim(), it) }
                } else {
                    activity?.toast(R.string.please_enter_correct_age)
                }
            }
        }
    }

    private fun changeName(name : String?,workerId : String){
        workersUpdateViewModel.updateWorkerName(name,workerId)
    }

    private fun changeSalary(salary : String?,workerId : String){
        workersUpdateViewModel.updateWorkerSalary(salary,workerId)
    }

    private fun changeAge(age : String?,workerId : String){
        workersUpdateViewModel.updateWorkerAge(age,workerId)
    }


    private fun checkState(inputEditText: AppCompatEditText?, imageView: AppCompatImageView?): Boolean {

        if (imageView == null) return false

        return if (imageView.tag == "Edit") {
            imageView.setImageDrawable(activity?.let { it1 -> ContextCompat.getDrawable(it1, R.drawable.ic_save) })
            imageView.tag = "Save"
            inputEditText?.isFocusableInTouchMode = true
            inputEditText?.isFocusable = true
            inputEditText?.isEnabled = true
            inputEditText?.showKeyboard()
            false
        } else {
            imageView.tag = "Edit"
            imageView.setImageDrawable(context?.let { ContextCompat.getDrawable(it,R.drawable.ic_edit) })
            inputEditText?.isFocusableInTouchMode = false
            inputEditText?.isFocusable = false
            inputEditText?.isEnabled = false
            activity?.forceCloseKeyboard()
            true
        }
    }

}
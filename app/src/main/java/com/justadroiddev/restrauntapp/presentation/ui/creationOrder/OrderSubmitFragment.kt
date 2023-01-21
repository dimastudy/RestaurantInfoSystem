package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.FragmentSubmitOrderBinding
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class OrderSubmitFragment :
    BaseFragment<FragmentSubmitOrderBinding>(FragmentSubmitOrderBinding::inflate) {

    private val viewModel: OrderSubmitViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tables = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val arrayAdapter =
            ArrayAdapter<Int>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tables
            )
        var localDate = LocalDate.now()
        var localTime = LocalTime.now()
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            localTime = LocalTime.of(hourOfDay, minute)
            binding.dateText.setText(formatTime(LocalDateTime.of(localDate, localTime)))
        }
        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            localDate = LocalDate.of(year, month + 1, dayOfMonth)
            TimePickerDialog(
                requireContext(),
                timeListener,
                localTime.hour,
                localTime.minute,
                true
            ).show()
        }
        binding.apply {
            orderPrice.text = "Вартість замовлення: ${OrderCreator.takeOrderPrice()}$"
            listTables.adapter = arrayAdapter
            isDeliveryChecked(checkDelivery.isChecked)
            checkDelivery.setOnCheckedChangeListener { buttonView, isChecked ->
                isDeliveryChecked(isChecked)
            }

            dateText.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateListener,
                    localDate.year,
                    localDate.monthValue - 1,
                    localDate.dayOfMonth
                ).show()
            }

            binding.submitButton.setOnClickListener {
                progressOrder.visibility = View.VISIBLE
                if (checkDelivery.isChecked) {
                    if (textAddress.text.toString().isNotEmpty() && dateText.text.toString()
                            .isNotEmpty()
                    ) {
                        val dateTimeText = formatTime(localDate, localTime)
                        viewModel.makeOrder(
                            textAddress.text.toString(),
                            dateTimeText,
                            true,
                            0
                        )
                    } else {
                        Snackbar.make(
                            requireView(),
                            "Всі поля повинні бути заповнені!",
                            Snackbar.LENGTH_LONG
                        ).show()
                        progressOrder.visibility = View.GONE
                    }
                } else {
                    if (listTables.selectedItem.toString().isNotEmpty() && dateText.text.toString()
                            .isNotEmpty()
                    ) {
                        val dateTimeText = formatTime(localDate, localTime)
                        viewModel.makeOrder(
                            textAddress.text.toString(),
                            dateTimeText,
                            false,
                            listTables.selectedItem.toString().toInt()
                        )
                    } else {
                        Snackbar.make(
                            requireView(),
                            "Всі поля повинні бути заповнені!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    progressOrder.visibility = View.GONE
                }
            }

        }

        viewModel.observeResult(viewLifecycleOwner) { result ->
            if (result != null) {
                if (result) {
                    binding.progressOrder.visibility = View.GONE
                    this.findNavController().navigate(R.id.menu_nav, null, navOptions {
                        popUpTo(R.id.orders_nav) {
                            inclusive = true
                        }
                    })
                    viewModel.orderDone()
                    viewModel.doneResult()
                } else {
                    binding.progressOrder.visibility = View.GONE
                    Snackbar.make(
                        requireView(),
                        "Виникла помилка! Спробуйте пізніше",
                        Snackbar.LENGTH_LONG
                    ).show()
                    viewModel.doneResult()
                }
            }
        }

    }

    fun formatTime(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return localDateTime.format(formatter)
    }

    fun formatTime(localDate: LocalDate, localTime: LocalTime): String {
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm")
        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateText = localDate.format(formatterDate)
        val timeText = localTime.format(formatterTime)
        return "${dateText}T${timeText}Z"
    }


    fun isDeliveryChecked(isChecked: Boolean) {
        if (isChecked) {
            binding.tableMessage.visibility = View.INVISIBLE
            binding.listTables.visibility = View.INVISIBLE
            binding.textInputLayout5.visibility = View.VISIBLE
        } else {
            binding.tableMessage.visibility = View.VISIBLE
            binding.listTables.visibility = View.VISIBLE
            binding.textInputLayout5.visibility = View.INVISIBLE
        }
    }

}
package com.example.networkexample.presentation.valutelist

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkexample.R
import com.example.networkexample.domain.model.SharedViewModel
import com.example.networkexample.domain.model.Valute
import com.example.networkexample.presentation.valutelist.list.ValuteListAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator


class ValuteListFragment : DialogFragment(), ValuteListView {

    companion object {
        const val KEY_CHOOSE_RECEIVER = "KEY_CHOOSE_RECEIVER"

        fun newInstance(key: String): ValuteListFragment {
            val args = Bundle()
            args.putString(KEY_CHOOSE_RECEIVER, key)
            val fragment = ValuteListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: LinearProgressIndicator
    private lateinit var viewModel: SharedViewModel
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private var presenter: ValuteListPresenter = ValuteListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDialog)
    }

    override fun onStart() {
        super.onStart()
        val d = dialog
        if (d != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            d.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_vatute_list, container, false)
        recyclerView = v.findViewById(R.id.recycler_view_character)
        progress = v.findViewById(R.id.progress)
        toolbar = v.findViewById(R.id.toolbar_fragment_valute_list)
        if (!(presenter.isNetworkAvailable(requireContext()))) presenter.getValutesFromDb()
        else
        presenter.onViewCreated()
        initListener()
        return v
    }


    private fun initListener() {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_back -> {
                    super.dismiss()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }


    override fun setupItemList(list: List<Valute>) {
        val tag = arguments?.getString(KEY_CHOOSE_RECEIVER)

        val adapter = ValuteListAdapter(list) {
            if (tag.equals(getString(R.string.start), true)) {
                viewModel.sendStartCharCode(it)
                dismiss()
            }
            if (tag.equals(getString(R.string.end), true)) {
                viewModel.sendEndCharCode(it)
                dismiss()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val decorator = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(decorator)
        recyclerView.adapter = adapter
    }

    override fun setProgressVisible(isVisible: Boolean) {
        progress.isVisible = isVisible
    }

}
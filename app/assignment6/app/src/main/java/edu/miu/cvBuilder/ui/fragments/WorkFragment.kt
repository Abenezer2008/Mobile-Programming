package edu.miu.cvBuilder.ui.fragments

import CVBuilderApp.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cvBuilder.domain.Work
import edu.miu.cvBuilder.ui.dialog.WorkDialog
import edu.miu.cvBuilder.adapter.WorkViewAdapter

class WorkFragment : Fragment(R.layout.fragment_work) {

    private var workList = mutableListOf<Work>()
    private lateinit var viewAdapter: WorkViewAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        if (context != null) {
            workList = mutableListOf(

                Work(
                    getString(R.string.work3),
                    getString(R.string.position_3),
                    getString(R.string.work3_tenure),
                    R.drawable.kforce,
                    getString(R.string.work3_desc)
                )
              ,
                Work(
                    getString(R.string.work2),
                    getString(R.string.position_2),
                    getString(R.string.work2_tenure),
                    R.drawable.better,
                    getString(R.string.work2_desc)

                ),
                Work(
                    getString(R.string.work1),
                    getString(R.string.position_1),
                    getString(R.string.work1_tenure),
                    R.drawable.tera,
                    getString(R.string.work1_desc)
                )
            )
            setupRecyclerView()
        }

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { showWorkDialog() }
    }

    private fun setupRecyclerView() {
        if (::recyclerView.isInitialized) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            viewAdapter = WorkViewAdapter(requireContext(), workList)
            recyclerView.adapter = viewAdapter
        }
    }

    private fun showWorkDialog() {
        val dialog = WorkDialog()
        dialog.show(parentFragmentManager, WorkDialog::class.java.name)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onAddWOrk(work: Work) {
        workList.add(work)
        if (::viewAdapter.isInitialized) {
            viewAdapter.notifyDataSetChanged()
        } else {
            setupRecyclerView()
        }
    }

}
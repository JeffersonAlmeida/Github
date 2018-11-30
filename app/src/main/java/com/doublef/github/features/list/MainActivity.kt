package com.doublef.github.features.list

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.doublef.github.R
import com.doublef.github.features.details.DetailsActivity
import com.doublef.github.features.util.IntentConstants
import com.doublef.github.model.RepositoryItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), ItemClickListener{

    private val viewModel: MainViewModel by inject()

    override fun onClickItem(repositoryItem: RepositoryItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(IntentConstants.ITEM, repositoryItem)
        startActivity(intent)
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = Adapter()
        adapter.onClickItemClickListener = this@MainActivity
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            recyclerView.adapter = adapter
        }
        viewModel.listData.observe(this@MainActivity as LifecycleOwner, Observer { repositoriesList ->
            adapter.data = repositoriesList
            adapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this@MainActivity as LifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading!!) View.VISIBLE else View.GONE
        })
        viewModel.hadNetworkError.observe(this@MainActivity as LifecycleOwner, Observer { hadNetworkError ->
            if ( hadNetworkError!! ) showDialog()
        })
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.error_title)
            setMessage(R.string.error_msg)
            setNegativeButton(android.R.string.cancel) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            setPositiveButton(R.string.error_try_again) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
                viewModel.fetchRepositories()
            }
            show()
        }
    }



}

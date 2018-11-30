package com.doublef.github.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.doublef.github.R
import com.doublef.github.features.util.IntentConstants
import com.doublef.github.model.RepositoryItem
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val item = intent.getParcelableExtra<RepositoryItem>(IntentConstants.ITEM)
        name.text = item.name
        description.text = item.description
    }
}

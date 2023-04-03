package com.whale.expandablelistviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.whale.expandablelistviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MenuAdapter

    private var categoryList = mutableListOf(
        Category("Kahveler"),
        Category("Atıştımalıklar"),
        Category("Diğer içecekler")
    )

    private var productList = mutableMapOf<Category, List<Product>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coffeeList = listOf(
            Product(
                ResourcesCompat.getDrawable(resources, R.drawable.image_americano, null),
                "Americano",
                33
            ),
            Product(
                ResourcesCompat.getDrawable(resources, R.drawable.image_latte, null),
                "Latte",
                37
            )
        )

        val snackList = listOf(
            Product(
                ResourcesCompat.getDrawable(resources, R.drawable.image_croissant, null),
                "Croissant",
                29
            )
        )

        val otherDrinksList = listOf(
            Product(
                ResourcesCompat.getDrawable(resources, R.drawable.image_tea, null),
                "Tea",
                15
            )
        )

        productList[categoryList[0]] = coffeeList
        productList[categoryList[1]] = snackList
        productList[categoryList[2]] = otherDrinksList

        adapter = MenuAdapter(categoryList, productList)
        binding.expandableListView.setAdapter(adapter)

    }
}
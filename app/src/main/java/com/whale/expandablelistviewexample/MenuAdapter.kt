package com.whale.expandablelistviewexample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.whale.expandablelistviewexample.databinding.CategoryListGroupBinding
import com.whale.expandablelistviewexample.databinding.ProductListItemBinding

class MenuAdapter(
    private val groupList: List<Category>,
    private val childList: MutableMap<Category, List<Product>>
) : BaseExpandableListAdapter() {

    /**
     * Kaç adet grup öğesine sahip olduğumuzu return
     * ediyor.
     */
    override fun getGroupCount(): Int {
        Log.d("getGroupCount result: ", "${groupList.size}")
        return groupList.size
    }

    /**
     * Kaç adet child öğesine sahip olduğumuzu return
     * ediyor.
     */
    override fun getChildrenCount(groupPosition: Int): Int {
        Log.d("getChildrenCount result: ", "${childList[groupList[groupPosition]]?.size ?: 0}")
        return childList[groupList[groupPosition]]?.size ?: 0
    }

    /**
     * Group öğesi get ediyor. Bu ilk implement edildiğinde
     * Any return ediyordu fakat ben değiştirdim.
     */
    override fun getGroup(groupPosition: Int): Category {
        Log.d("getGroup result: ", "${groupList[groupPosition]}")
        return groupList[groupPosition]
    }

    /**
     * Child öğesi get ediyor. Bu ilk implement edildiğinde
     * Any return ediyordu fakat ben değiştirdim.
     */
    override fun getChild(groupPosition: Int, childPosition: Int): Product {
        Log.d(
            "getChild result: ",
            "${
                childList[groupList[groupPosition]]?.get(childPosition) ?: Product(
                    null,
                    null,
                    null
                )
            }"
        )
        return childList[groupList[groupPosition]]?.get(childPosition) ?: Product(null, null, null)
    }

    /**
     * Group öğesi için Id get ediyor.
     */
    override fun getGroupId(groupPosition: Int): Long {
        Log.d("getGroupId result: ", "${groupPosition.toLong()}")
        return groupPosition.toLong()
    }

    /**
     * Child öğesi için Id get ediyor.
     */
    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        Log.d("getChildId result: ", "${childPosition.toLong()}")
        return childPosition.toLong()
    }

    /**
     * Öğelerinin benzersiz bir kimliğe sahip olup olmadığını
     * return ediyor.
     */
    override fun hasStableIds(): Boolean {
        return true
    }

    /**
     * Group öğe View'ı get ediyor.
     */
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding: CategoryListGroupBinding =
            CategoryListGroupBinding.inflate(LayoutInflater.from(parent?.context))
        binding.textViewCategoryListGroup.text = getGroup(groupPosition).name
        return binding.root
    }

    /**
     * Child öğe View'ı get ediyor.
     */
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding: ProductListItemBinding =
            ProductListItemBinding.inflate(LayoutInflater.from(parent?.context))

        binding.apply {
            TextViewProductName.text = getChild(groupPosition, childPosition).name
            TextViewProductPrice.text = getChild(groupPosition, childPosition).price.toString()
            ImageViewProduct.setImageDrawable(getChild(groupPosition, childPosition).image)
            return binding.root
        }
    }

    /**
     * groupPosition ve childPosition parametreleri belirttiğimiz
     * child öğenin selectable olup olmamasını bu fonksyion ile
     * yönetiyoruz
     */
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
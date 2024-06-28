import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forest.R
import com.example.forest.ui.models.CategoryChipModel

class CategoryFilterFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryChipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_category_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_category_filter_chip)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val chipItems = listOf(
            CategoryChipModel("Northern Europe", true),
            CategoryChipModel("Western Europe", true),
            CategoryChipModel("Southern Europe", true),
            CategoryChipModel("Southeast Europe", true),
            CategoryChipModel("Central Europe", true),
            CategoryChipModel("Eastern Europe", false)
        )

        adapter = CategoryChipsAdapter(chipItems)
        recyclerView.adapter = adapter
    }
}
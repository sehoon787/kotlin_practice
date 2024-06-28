import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forest.R
import com.example.forest.ui.models.CategoryChipModel
import com.google.android.material.chip.Chip

class CategoryChipsAdapter(
    private val chipItems: List<CategoryChipModel>
) : RecyclerView.Adapter<CategoryChipsAdapter.ChipViewHolder>() {

    inner class ChipViewHolder(val chip: Chip) : RecyclerView.ViewHolder(chip) {
        fun bind(chipItem: CategoryChipModel) {
            chip.text = chipItem.name
            chip.isChecked = chipItem.isChecked
            chip.setOnCheckedChangeListener { _, isChecked ->
                chipItem.isChecked = isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val chip = LayoutInflater.from(parent.context).inflate(R.layout.rv_category_item_chip, parent, false) as Chip
        return ChipViewHolder(chip)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        holder.bind(chipItems[position])
    }

    override fun getItemCount() = chipItems.size
}

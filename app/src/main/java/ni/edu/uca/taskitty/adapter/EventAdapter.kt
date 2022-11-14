package ni.edu.uca.taskitty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.databinding.EventItemBinding
import ni.edu.uca.taskitty.model.Event

class EventAdapter(
    private var eventList: List<Event>
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private lateinit var binding: EventItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        this.binding = EventItemBinding.inflate(inflater, parent, false)
        return ViewHolder(this.binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]
        holder.render(event)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    class ViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val eventTitle = binding.tvEventTitle
        private val eventTime = binding.tvLeftTime

        fun render(event: Event) {
            eventTitle.text = event.title
            eventTime.text = event.dateEnd.month.toString()
        }
    }
}
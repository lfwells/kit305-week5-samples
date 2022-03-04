package layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week05tabs.databinding.FragmentGradesBinding
import com.example.week05tabs.databinding.FragmentMainBinding

class GradesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var inflatedView = FragmentGradesBinding.inflate(layoutInflater, container, false)

        //do normal oncreate things in here, e.g.
        //btnCamera.setOnClickListener ...

        return inflatedView.root
    }
}
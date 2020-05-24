package com.example.whattowatch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.whattowatch.R
import com.example.whattowatch.api.IMovieAPI
import com.example.whattowatch.model.mymodel.MyPersonModel
import com.example.whattowatch.ui.presenter.PersonPresenter
import com.example.whattowatch.ui.view.PersonView

class PersonFragment : BaseFragment(), PersonView {
    private var tvName: TextView? = null
    private var tvDate: TextView? = null
    private var tvPlace: TextView? = null
    private var tvBiography: TextView? = null
    private var ivAvatar: ImageView? = null
    @InjectPresenter
    var presenter: PersonPresenter? = null

    @ProvidePresenter
    fun providePersonPresenter(): PersonPresenter {
        return PersonPresenter(arguments!!.getInt(ARG_PERSON_ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.person_fragment, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        tvDate = view.findViewById(R.id.tv_birthday)
        tvPlace = view.findViewById(R.id.tv_born_place)
        tvName = view.findViewById(R.id.tv_name_person)
        tvBiography = view.findViewById(R.id.tv_bio)
        ivAvatar = view.findViewById(R.id.iv_avatar)
    }

    override fun showProgress(isVisible: Boolean) {}
    override fun showPersonInfo(personModel: MyPersonModel) {
        tvName!!.text = personModel.name
        tvPlace!!.text = personModel.placeOfBirth
        tvDate!!.text = personModel.birthday
        tvBiography!!.text = personModel.biography
        Glide.with(context!!)
                .load(IMovieAPI.BASE_PICTURE + personModel.profilePath)
                .fitCenter()
                .into(ivAvatar!!)
    }

    override fun showError() {}

    companion object {
        private const val ARG_PERSON_ID = "id_person"
        @JvmStatic
        fun newInstance(idPerson: Int): PersonFragment {
            val args = Bundle()
            args.putInt(ARG_PERSON_ID, idPerson)
            val fragment = PersonFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
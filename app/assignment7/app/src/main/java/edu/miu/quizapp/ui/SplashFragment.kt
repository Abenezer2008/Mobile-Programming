package edu.miu.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.db.Quiz
import edu.miu.quizapp.db.QuizDatabase
import edu.miu.quizapp.utils.BaseFragment
import edu.miu.quizapp.utils.PrefManager
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private lateinit var tvWelcome: TextView
    private var prefManager: PrefManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefManager = PrefManager(context)
         val view = inflater.inflate(R.layout.fragment_splash, container, false)
        tvWelcome = view.findViewById(R.id.logo_welcome)
        addToDb()
        return view
    }

    override fun onResume(){
        super.onResume()
        tvWelcome.postDelayed({
            if (!prefManager?.isFirstTimeLaunch()!!) {
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_welcomeFragment)
            }
        }, 1000)
    }

    fun addToDb(){
        val q1 = Quiz(1L,"1. Kotlin is","A. a programming Language", "B. a scripting language","C. an operating system","D. None of the above","a")
        val q2 = Quiz(2L,"2. How many key components does Android architecture has?", "A. One","B. Three","C. Five","D. Four","c")
        val q3 = Quiz(3L,"3. Which came first","A. Java", "B. C ++","C. C","D. Kotlin","c")
        val q4 = Quiz(4L,"4. How many possible states are there for a process?","A. Four", "B. Three","C. Two","D. One","a")
        val q5 = Quiz(5L,"5. Java is best described as","A. Object Oriented", "B. Procedure Oriented","C. Both","D. None of the above","a")

        val q6 = Quiz(6L,"6. Android is based on ","A. Unix", "B. Linux","C. Both","D. None of the above","b")
        val q7 = Quiz(7L,"7. Kotlin is developed by","A. Google", "B. Facebook","C. JetBrains","D. Apple","c")
        val q8 = Quiz(8L,"8. What is the extension of compiled Java classes","A. .class", "B. .java","C. .dex","D. .txt","a")
        val q9 = Quiz(9L,"9. Which of the following converts Java byte code into Dalvik byte code?","A. Dalvik converter", "B. Dex compiler","C. Mobile interpretive compiler (MIC)","D. None of the above","b")
        val q10 = Quiz(10L,"10. When an activity is no longer in a memory, it is said to be","A. Active", "B. Paused","C. Stopped","D. Destroyed","d")

        val q11 = Quiz(11L,"11. What is an activity in android?","A. Android class", "B. What your app does, and how it interacts with the user","C. A single screen in an application with supporting java code","D. B and C","d")
        val q12 = Quiz(12L,"12. Which one of the following helps to organize the UI components?","A. Activity", "B. Layout","C. AVD","D. Gradle","b")
        val q13 = Quiz(13L,"13. AVD stands for","A. Android Virtual Defence", "B. Android Virtual Device","C. Application Virus Defence","D. None of the above","b")
        val q14 = Quiz(14L,"14. When did android first came out", "2002", "B. 2004","C. 2006","D. 2008","d")
        val q15 = Quiz(15L,"15. Kotlin best described as","A. Strictly typed", "B. Statically typed","C. Untyped","D. None of the above","b")

        launch {
            context?.let {
                QuizDatabase(it)
                    .getQuizDao().deleteAll()
                QuizDatabase(it)
                    .getQuizDao().addMany(q1,q2,q3,q4,q5,q6,q7,q8,
                    q9,q10,q11,q12,q13,q14,q15)
            }
        }
    }

}
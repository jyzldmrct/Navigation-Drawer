package ph.edu.auf.navigationdrawerlesson

import android.app.Application
import android.content.Context

class NavigationDrawerLessonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }

    companion object {
        lateinit var context: Context
            private set
    }

}
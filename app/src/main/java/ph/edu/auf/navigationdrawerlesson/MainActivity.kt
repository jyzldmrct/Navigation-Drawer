package ph.edu.auf.navigationdrawerlesson

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import ph.edu.auf.navigationdrawerlesson.databinding.ActivityMainBinding
import ph.edu.auf.navigationdrawerlesson.fragments.FavoritesQuotesFragment
import ph.edu.auf.navigationdrawerlesson.fragments.HomeFragment
import ph.edu.auf.navigationdrawerlesson.fragments.InspiringQuotesFragment
import ph.edu.auf.navigationdrawerlesson.fragments.LoveQuotesFragment
import ph.edu.auf.navigationdrawerlesson.fragments.MotivationalQuotesFragment
import ph.edu.auf.navigationdrawerlesson.fragments.QuotesOfTheDayFragment

class MainActivity : AppCompatActivity(),
    HomeFragment.OnOpenDrawerListener,
    InspiringQuotesFragment.OnOpenDrawerListener,
    MotivationalQuotesFragment.OnOpenDrawerListener,
    LoveQuotesFragment.OnOpenDrawerListener,
    QuotesOfTheDayFragment.OnOpenDrawerListener,
    FavoritesQuotesFragment.OnOpenDrawerListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_quotes_day, R.id.nav_love_quotes, R.id.nav_fave_quotes, R.id.nav_inspiring_quotes, R.id.nav_motivational_quotes
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOpenDrawer() {
        binding.drawerLayout.openDrawer(binding.navView)
    }
}
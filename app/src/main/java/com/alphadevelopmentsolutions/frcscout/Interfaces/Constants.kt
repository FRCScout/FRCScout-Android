package com.alphadevelopmentsolutions.frcscout.Interfaces

import com.alphadevelopmentsolutions.frcscout.Activities.MainActivity
import com.alphadevelopmentsolutions.frcscout.BuildConfig
import java.io.File

interface Constants
{

    interface SharedPrefKeys
    {
        companion object
        {
            //Shared Pref Keys
            const val API_KEY_KEY = "API_KEY" //key to access web

            const val API_CORE_USERNAME = "API_CORE_USERNAME"
            const val API_CORE_PASSWORD = "API_CORE_PASSWORD"

            const val TEAM_NUMBER_KEY = "TEAM_NUMBER"
            const val TEAM_NAME_KEY = "TEAM_NAME"
            const val APP_NAME_KEY = "APP_NAME"

            const val PRIMARY_COLOR_KEY = "PRIMARY_COLOR"
            const val PRIMARY_COLOR_DARK_KEY = "PRIMARY_COLOR_DARK"
            const val TEAM_ROBOT_MEDIA_DIR_KEY = "ROBOT_MEDIA_DIR"

            const val SELECTED_EVENT_KEY = "SELECTED_EVENT"
            const val SELECTED_YEAR_KEY = "SELECTED_YEAR"

            const val DOWNLOAD_EVENTS_KEY = "DOWNLOAD_EVENTS"
            const val DOWNLOAD_MATCHES_KEY = "DOWNLOAD_MATCHES"
            const val DOWNLOAD_TEAMS_KEY = "DOWNLOAD_TEAMS"
            const val DOWNLOAD_CHECKLISTS_KEY = "DOWNLOAD_CHECKLISTS"
            const val DOWNLOAD_ROBOT_INFO_KEY = "DOWNLOAD_ROBOT_INFO"
            const val DOWNLOAD_SCOUT_CARD_INFO_KEY = "DOWNLOAD_SCOUT_CARD_INFO"
            const val DOWNLOAD_ROBOT_MEDIA_KEY = "DOWNLOAD_ROBOT_MEDIA"
            
            const val UPLOAD_CHECKLISTS_KEY = "UPLOAD_CHECKLISTS"
            const val UPLOAD_ROBOT_INFO_KEY = "UPLOAD_ROBOT_INFO"
            const val UPLOAD_SCOUT_CARD_INFO_KEY = "UPLOAD_SCOUT_CARD_INFO"
            const val UPLOAD_ROBOT_MEDIA_KEY = "UPLOAD_ROBOT_MEDIA"
        }
    }

    companion object
    {
        /**
         * Gets the directory for the file system
         * @param context [MainActivity] used to get the apps data dir
         * @param dir [String] directory to save retrieve
         */
        fun getFileDirectory(context: MainActivity, dir: String): File
        {
            return context.getExternalFilesDir(dir)!!
        }

        const val ROBOT_MEDIA_DIRECTORY = "robot-media"
        const val YEAR_MEDIA_DIRECTORY = "year-media"

        const val ROBOT_MEDIA_REQUEST_CODE = 5885

        const val WEB_URL = BuildConfig.API_URL
        const val API_URL = "$WEB_URL/api/"

        const val RECAPTCHA_SITE_KEY = "6Lftl74UAAAAACuMDDwCATDi2sQnQfqK_c4psVt_"

    }

}

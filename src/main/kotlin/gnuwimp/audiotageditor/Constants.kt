/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

/***
 *       _____                _              _
 *      / ____|              | |            | |
 *     | |     ___  _ __  ___| |_ __ _ _ __ | |_ ___
 *     | |    / _ \| '_ \/ __| __/ _` | '_ \| __/ __|
 *     | |___| (_) | | | \__ \ || (_| | | | | |_\__ \
 *      \_____\___/|_| |_|___/\__\__,_|_| |_|\__|___/
 *
 *
 */

/**
 * All text strings for the application.
 */
object Constants {
    const val APP_NAME                    = "AudioTagEditor"
    const val DIALOG_ABOUT                = "About AudioTagEditor"
    const val DIALOG_LOG                  = "AudioTagEditor Log"
    const val DIALOG_TITLE_LOAD           = "Loading Tracks"
    const val DIALOG_TITLE_SAVE           = "Saving Tracks"
    const val DIALOG_TITLE_SETUP          = "Setup"

    const val LABEL_ABOUT                 = "About..."
    const val LABEL_ALBUM                 = "Album"
    const val LABEL_ALBUM_ARTIST          = "Album artist"
    const val LABEL_APPEND_TEXT           = "Append text"
    const val LABEL_ARTIST                = "Artist"
    const val LABEL_BITRATE               = "Bitrate"
    const val LABEL_COMMENT               = "Comment"
    const val LABEL_COMPOSER              = "Composer"
    const val LABEL_COPY_ARTIST           = "Copy tags"
    const val LABEL_COVER                 = "Cover"
    const val LABEL_DELETE_TAGS           = "Delete tags..."
    const val LABEL_DELETE_TRACKS         = "Delete tracks..."
    const val LABEL_ENCODER               = "Encoder"
    const val LABEL_EXTENSION             = "Extension"
    const val LABEL_FILE                  = "File"
    const val LABEL_FILENAME              = "Filename"
    const val LABEL_FILEPATH              = "Path"
    const val LABEL_FILTER                = "Select..."
    const val LABEL_FORMAT                = "Format"
    const val LABEL_GENRE                 = "Genre"
    const val LABEL_GENRE_GROUP           = "Genre groups"
    const val LABEL_INSERT_ALBUM          = "Insert album"
    const val LABEL_INSERT_ARTIST         = "Insert artist"
    const val LABEL_INSERT_TEXT           = "Insert text"
    const val LABEL_LOAD_IMAGE            = "Load cover image..."
    const val LABEL_MOVE_DOWN             = "Move down"
    const val LABEL_MOVE_UP               = "Move up"
    const val LABEL_NUMBER_SEP            = "Number separator"
    const val LABEL_PREVIEW_CHANGES       = "Preview changes"
    const val LABEL_QUIT                  = "Quit"
    const val LABEL_RELOAD_DIR            = "Reload directory"
    const val LABEL_RELOAD_DIR_REC        = "Reload recursive"
    const val LABEL_REMOVE_COVER          = "Remove cover image"
    const val LABEL_REMOVE_ILLEG          = "Remove illegal Characters"
    const val LABEL_REMOVE_LEAD           = "Remove leading"
    const val LABEL_REMOVE_TEXT           = "Remove text"
    const val LABEL_REMOVE_TEXT_REGEX     = "Use regex in remove/replace"
    const val LABEL_REMOVE_TRAIL          = "Remove trailing"
    const val LABEL_REPLACE_TEXT          = "Replace text"
    const val LABEL_RESIZE_ALL            = "Resize all..."
    const val LABEL_RESIZE_COVER          = "Resize cover..."
    const val LABEL_RESET                 = "Reset options"
    const val LABEL_SAVE                  = "Save all changed tracks"
    const val LABEL_SELECT                = "Select"
    const val LABEL_SELECT_ALL            = "Select all"
    const val LABEL_SELECT_NONE           = "Unselect all"
    const val LABEL_SET_FILENAME          = "Set filename"
    const val LABEL_SET_TITLE             = "Set title"
    const val LABEL_SET_YEAR              = "Set year"
    const val LABEL_SETUP                 = "Setup..."
    const val LABEL_SETUP_IMAGE           = "Default image resize size (200 - 2000)"
    const val LABEL_SETUP_FONT            = "Set font size (8 - 24) - restart to apply"
    const val LABEL_SETUP_THREAD          = "Set thread count (1 - 32)"
    const val LABEL_SHOW_LOG              = "Show log..."
    const val LABEL_SIZE                  = "Size"
    const val LABEL_START_TRACK           = "Start track"
    const val LABEL_TAB_ALBUM             = "Rename album tags"
    const val LABEL_TAB_FILE              = "Rename filenames"
    const val LABEL_TAB_TITLE             = "Rename title tags"
    const val LABEL_TAB_TRACK             = "Edit tracks"
    const val LABEL_TIME                  = "Time"
    const val LABEL_TITLE                 = "Title"
    const val LABEL_TRACK                 = "Track"
    const val LABEL_UNDO                  = "Undo all changes..."
    const val LABEL_USE_FILENAME          = "Use filename"
    const val LABEL_USE_TITLE             = "Use title"
    const val LABEL_YEAR                  = "Year"

    const val DEF_INSERT_ALBUM            = " - "
    const val DEF_INSERT_ARTIST           = " - "
    const val DEF_NUMBER_SEP              = " "
    const val DEF_START_TRACK             = "1"

    const val ERROR_ABORTING_READING      = "Aborting reading tracks!"
    const val ERROR_ABORTING_SAVING       = "Aborting saving tracks!"
    const val ERROR_DELETE_TAGS           = "Deleting tags from track failed!\n%s"
    const val ERROR_LOADING               = "Error - loaded %d track(s) and %d track(s) failed or skipped from %s %s!"
    const val ERROR_LOADING_TRACK         = "Error - can't read file %s!"
    const val ERROR_RENAME                = "Error - can't rename track!"
    const val ERROR_RESIZING_TRACKS       = "Error - resized %d cover images but %d failed!"
    const val ERROR_SAVE                  = "<b>Saving tracks failed!</b>\nCheck the log what track(s) failed."
    const val ERROR_SAVING1               = "Error - saved %d track(s) and %d track(s) failed %s!"
    const val ERROR_SAVING2               = "Error - saved %d track(s) but there were some errors!"
    const val ERROR_SAVING3               = "Error - failed to save %d tracks!"

    const val MESSAGE_ASK_DELETE_FILES    = "<b>Do you want to delete all selected tracks?</b>\nEnter YES to delete the files!"
    const val MESSAGE_ASK_DELETE_TAGS     = "<b>Do you want to delete all tags from all selected tracks?</b>\nAll tracks will be saved immediately.\nYou can't undo this.\nEnter YES to delete the tags!"
    const val MESSAGE_ASK_FILTER_ALBUM    = "<b>Enter text to search for in artist, album and genre fields</b>.\nAll found tracks will be selected.\nCase are ignored."
    const val MESSAGE_ASK_FILTER_FILE     = "<b>Enter text to search for in file names.</b>\nAll found tracks will be selected.\nCase are ignored."
    const val MESSAGE_ASK_FILTER_TITLE    = "<b>Enter text to search for in titles.</b>\nAll found tracks will be selected.\nCase are ignored."
    const val MESSAGE_ASK_IMAGE_SIZE      = "<b>Enter image size.</b>\nFrom 200 to 2000 pixels.\nAspect ratio will be kept."
    const val MESSAGE_ASK_SAVE            = "<b>You have modified tracks!</b>\nDo you want to save changes?"
    const val MESSAGE_ASK_UNDO            = "<b>Undo all changes for all selected tracks?</b>\nAll data will be restored."
    const val MESSAGE_CHANGED_TRACKS      = "%d tracks was changed"
    const val MESSAGE_LOADING             = "Loaded %d track(s) from %s %s"
    const val MESSAGE_LOADING_TRACK       = "Loading file %s"
    const val MESSAGE_LOADING_TRACKS      = "Loading tracks from %s"
    const val MESSAGE_REMOVED_COVER       = "Removed cover image for %s"
    const val MESSAGE_RENAMING_TRACK      = "Renaming file %s to %s"
    const val MESSAGE_RESIZED_COVER       = "Resized cover image for %s"
    const val MESSAGE_RESIZING_COVERS     = "Resized %d cover images"
    const val MESSAGE_SAVING              = "Saved %d track(s) %s"
    const val MESSAGE_SAVING_TRACK        = "Saving file %s"
    const val MESSAGE_TIME                = "in %s mS"

    const val TOOL_ABOUT                  = "Show about dialog."
    const val TOOL_ALBUM                  = "Select to change album text for all selected tracks."
    const val TOOL_ALBUM_ARTIST           = "Set album artist for all selected tracks."
    const val TOOL_APPEND_TEXT            = "Enter text to append AFTER filename for all selected tracks."
    const val TOOL_APPLY_CHANGES          = "Apply changes to all selected track but do NOT save them."
    const val TOOL_ARTIST                 = "Select to change artist text for all selected tracks."
    const val TOOL_COMMENT                = "Select to change comment text for all selected tracks."
    const val TOOL_COMPOSER               = "Select to change composer text for all selected tracks."
    const val TOOL_COPY_ARTIST            = "Copy artist text to album artist or vice versa."
    const val TOOL_COVER                  = "<html>Select to change cover image for all selected tracks.<br>Load image first or use default image to remove covers.<html>"
    const val TOOL_COVER_SHOW             = "Click to see full image."
    const val TOOL_DELETE_COVER           = "Delete cover image for selected track."
    const val TOOL_DELETE_TAGS            = "<html>Clear all tags from all tracks.<br>All cleared tracks are saved automatically!</html>"
    const val TOOL_DELETE_TRACKS          = "Delete selected tracks from disk!"
    const val TOOL_DIRTREE_DISABLED       = "Save or undo changes before selecting another directory."
    const val TOOL_DIRTREE_ENABLED        = "Select directory with audio files."
    const val TOOL_ENCODER                = "Select to change encoder for all selected tracks."
    const val TOOL_EXT_CAP                = "Change file extension capitalisation."
    const val TOOL_GENRE                  = "Select to change genre text for all selected tracks."
    const val TOOL_INSERT_ALBUM           = "<html>Insert album BEFORE text.<br>Enter optional separator between album and text.<br>Default separator is ' - '.</html>"
    const val TOOL_INSERT_ARTIST          = "<html>Insert artist BEFORE text.<br>Enter optional separator between artist and text.<br>Default separator is ' - '.</html>"
    const val TOOL_INSERT_TEXT            = "Enter text to insert BEFORE text."
    const val TOOL_LOAD_IMAGE             = "Load cover image from disk."
    const val TOOL_MOVE_DOWN              = "Move selected track down in the track list."
    const val TOOL_MOVE_UP                = "Move selected track up in the track list."
    const val TOOL_NAME_CAP               = "Change filename capitalisation."
    const val TOOL_NUMBER                 = "Insert or append track number before filename."
    const val TOOL_NUMBER_SEP             = "<html>Separator string to insert or append between track number and text.<br>Default separator is space.</html>"
    const val TOOL_QUIT                   = "Quit AudioTagEditor."
    const val TOOL_RELOAD                 = "Reload all tracks from current directory."
    const val TOOL_RELOAD_RECURSIVE       = "<html>Reload all tracks from current directory.<br>Load also all tracks in child directories.</html>"
    const val TOOL_REMOVE_ILLEG           = "Remove characters such as : and / and a few more that might cause problem on various OS."
    const val TOOL_REMOVE_LEAD            = "Remove all leading none letters from the text until first letter is found."
    const val TOOL_REMOVE_TEXT            = "<html>Enter text to remove from the text.<br>Try regular expressions such as <b>'^\\d\\d'</b> which removes the two first numbers.<br>Regular expression must be turned on.</html>"
    const val TOOL_REMOVE_TEXT_REGEX      = "Use regular expressions for removing text."
    const val TOOL_REMOVE_TRAIL           = "Remove all trailing none letters from the filename."
    const val TOOL_REPLACE_TEXT           = "Enter text to replace removed text with."
    const val TOOL_RESET                  = "Reset all options."
    const val TOOL_RESIZE_ALL             = "Resize cover image for all selected tracks."
    const val TOOL_RESIZE_COVER           = "Resize cover for current track."
    const val TOOL_SAVE                   = "<html>Save all SELECTED tracks that have been MODIFIED.<br>Changes can't be undone!</html>"
    const val TOOL_SELECT_ALBUM           = "Select tracks by searching artist, album and genre."
    const val TOOL_SELECT_ALL             = "Select all loaded tracks."
    const val TOOL_SELECT_FILE            = "Select tracks by searching file names."
    const val TOOL_SELECT_NONE            = "Unselect all loaded tracks."
    const val TOOL_SELECT_TITLE           = "Select tracks by searching titles."
    const val TOOL_SET_FILENAME           = "Enter text to use as the filename."
    const val TOOL_SET_TITLE              = "Set title to input text."
    const val TOOL_SET_YEAR               = "Select to change year for all selected tracks."
    const val TOOL_SHOW_LOG               = "Show log window."
    const val TOOL_START_TRACK            = "<html>Select to change track number for all selected tracks.<br>Set start track number.</html>"
    const val TOOL_TABLE_HEAD             = "<html>Click table header to sort rows.<br>Ctrl-click to reverse sort order.</html>"
    const val TOOL_TABLE_HEAD_FILE        = "<html>Click table header to sort rows.<br>Ctrl-click to reverse sort order.<br>Click on File to sort on path and filename.</html>"
    const val TOOL_TABLE_HEAD_TRACK       = "<html>Click table header to sort rows.<br>Ctrl-click to reverse sort order.<br>Click on File to sort on filename only.</html>"
    const val TOOL_TAB_ALBUM              = "Update album tags for all tracks."
    const val TOOL_TAB_FILE               = "Update file names for all tracks."
    const val TOOL_TAB_TITLE              = "Update title tags for all tracks."
    const val TOOL_TAB_TRACK              = "Edit single tracks."
    const val TOOL_TRACK                  = "Only tracknumbers from 1 to to 99999 are accepted."
    const val TOOL_UNDO                   = "Undo all changes to all selected tracks."
    const val TOOL_USE_FILENAME           = "Use filename as title."
    const val TOOL_USE_TITLE              = "If title exist, use it as the filename."
    const val TOOL_YEAR                   = "Only years from 1 to 9999 are accepted."

    const val COVER_EMBEDDED              = "cover"
    const val COVER_SCALED                = "scaled"
    const val COVER_REMOVED               = ""

    val OPTIONS_CAP_EXT: List<String>     = listOf("All extension lowercase", "All extension uppercase")
    val OPTIONS_CAP_NAME: List<String>    = listOf("Capitalize all words", "All letters lowercase", "All letters uppercase")
    val OPTIONS_CAP_TITLE: List<String>   = listOf("Capitalize all words", "All letters lowercase", "All letters uppercase")
    val OPTIONS_COPY_ARTIST: List<String> = listOf("Copy artist to 'album artist'", "Copy album to 'album artist'", "Copy artist + album to 'album artist'", "Copy 'album artist' to artist", "Copy 'album artist' to album")
    val OPTIONS_NUMBER: List<String>      = listOf("Insert track number", "Insert track number - 2 decimals", "Insert track number - 3 decimals", "Insert track number - 4 decimals", "Append track number", "Append track number - 2 decimals", "Append track number - 3 decimals", "Append track number - 4 decimals")

    const val ICON_SIZE                   = 240
    const val DEFAULT_THREADS             = 8
    const val MAX_THREADS                 = 32

    const val MIN_FONT                    = 8
    const val DEFAULT_FONT                = 14
    const val MAX_FONT                    = 24

    const val MIN_IMAGE_SIZE              = 200
    const val MAX_IMAGE_SIZE              = 2000

    /**
     *
     */
    fun aboutApp(): String {
        var about = "<html>" +

        "<b>AudioTagEditor 1.3</b><br><br>" +

        "<b>About</b><br>" +
        "Copyright 2021 - 2025 gnuwimp@gmail.com.<br>" +
        "Released under the GNU General Public License v3.0.<br>" +
        "See: <a href=\"https://github.com/gnuwimp/AudioTagEditor\">https://github.com/gnuwimp/AudioTagEditor</a><br>" +
        "<br>" +

        "AudioTagEditor is an audio file tag editor written in Kotlin.<br>" +
        "Use AudioTagEditor with caution and at your own risk.<br>" +
        "<br>" +

        "<b>Following third party software library are used</b><br>" +
        "JAudioTagger - <a href=\"http://www.jthink.net/jaudiotagger\">http://www.jthink.net/jaudiotagger</a><br>" +
        "<br>" +
        "<b>Versions</b><br>"

        about += "Java: " + System.getProperty("java.version") + "<br>"
        about += "Kotlin: " + KotlinVersion.CURRENT + "<br>"
        about += "JAudioTagger: 3.0.2-SNAPSHOT"
        about += "<html>"

        return about
    }
}

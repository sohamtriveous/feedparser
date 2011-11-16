#!/bin/bash -e
curl http://localhost:9000/view/FeedParser/job/FeedParser/lastSuccessfulBuild/artifact/feed_reader/bin/FeedParser-debug.apk -O FeedParser-debug.apk
curl http://localhost:9000/view/FeedParser/job/FeedParser/lastSuccessfulBuild/artifact/feed_reader_test/bin/FeedReaderTest-debug.apk -O FeedReaderTest-debug.apk
/Users/vaidy/Tools/android-sdk-macosx/platform-tools/adb -s 32352CC496CC00EC uninstall org.feedreader
/Users/vaidy/Tools/android-sdk-macosx/platform-tools/adb -s 32352CC496CC00EC uninstall org.feedreader.test
/Users/vaidy/Tools/android-sdk-macosx/platform-tools/adb -s 32352CC496CC00EC install -r FeedReaderTest-debug.apk
/Users/vaidy/Tools/android-sdk-macosx/platform-tools/adb -s 32352CC496CC00EC install -r FeedParser-debug.apk
/Users/vaidy/Tools/android-sdk-macosx/platform-tools/adb -s 32352CC496CC00EC  shell am instrument -w org.feedreader.test/android.test.InstrumentationTestRunner

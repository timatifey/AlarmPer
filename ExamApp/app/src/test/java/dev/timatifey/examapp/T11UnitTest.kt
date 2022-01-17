package dev.timatifey.examapp

import dev.timatifey.examapp.t11.IntentResolution
import org.junit.Assert
import org.junit.Test
import dev.timatifey.examapp.t11.MyIntentFilter

import dev.timatifey.examapp.t11.MyIntent
import java.net.URI


class T11UnitTest {

    @Test
    fun actionTest() {
        val intentWithAction = MyIntent().apply {
            action = "ACTION_VIEW"
            data = URI("tel:123")
        }
        val intentWithAnotherAction = MyIntent().apply {
            action = "ACTION_ANOTHER"
            data = URI("tel:123")
        }
        val intentWithoutAction = MyIntent().apply {
            data = URI("myscheme://show")
            extras["INFORMER"] = "widget"
        }
        val filterWithAction = MyIntentFilter().apply {
            actions.add("ACTION_VIEW")
            actions.add("ACTION_SEND")
        }
        val filterWithoutAction = MyIntentFilter().apply {
            scheme = "myscheme"
        }
        Assert.assertTrue(IntentResolution.match(intentWithAction, filterWithAction))
        Assert.assertFalse(IntentResolution.match(intentWithAction, filterWithoutAction))
        Assert.assertTrue(IntentResolution.match(intentWithoutAction, filterWithoutAction))
        Assert.assertFalse(IntentResolution.match(intentWithoutAction, filterWithAction))
        Assert.assertFalse(IntentResolution.match(intentWithAnotherAction, filterWithAction))
    }

    @Test
    fun categoriesTest() {
        val intent1 = MyIntent().apply {
            action = "ACTION_VIEW"
            categories.add("SOME_CAT1")
            data = URI("tel:123")
        }
        val intent2 = MyIntent().apply {
            action = "ACTION_VIEW"
            categories.add("SOME_CAT1")
            categories.add("SOME_CAT2")
            categories.add("SOME_CAT3")
            data = URI("tel:123")
        }
        val filter = MyIntentFilter().apply {
            actions.add("ACTION_VIEW")
            actions.add("ACTION_SEND")
            categories.add("SOME_CAT1")
            categories.add("SOME_CAT3")
        }
        Assert.assertFalse(IntentResolution.match(intent1, filter))
        Assert.assertTrue(IntentResolution.match(intent2, filter))
    }

    @Test
    fun schemeTest() {
        val intent1 = MyIntent().apply {
            data = URI("scheme1://somedata/1")
        }
        val intentEmptyData = MyIntent().apply {
            action = "ACTION_VIEW"
        }
        val intent2 = MyIntent().apply {
            data = URI("scheme2://show")
        }
        val scheme1Filter = MyIntentFilter().apply {
            scheme = "scheme1"
        }
        val scheme2Filter = MyIntentFilter().apply {
            scheme = "scheme2"
        }
        val scheme2PortFilter = MyIntentFilter().apply {
            scheme = "scheme2"
            port = "8080"
        }
        Assert.assertTrue(IntentResolution.match(intent1, scheme1Filter))
        Assert.assertFalse(IntentResolution.match(intent2, scheme1Filter))
        Assert.assertTrue(IntentResolution.match(intent2, scheme2Filter))
        Assert.assertFalse(IntentResolution.match(intent2, scheme2PortFilter))
        Assert.assertFalse(IntentResolution.match(intentEmptyData, scheme1Filter))
    }

    @Test
    fun typeTest() {
        val videoIntent = MyIntent().apply {
            data = URI("http://youtube.com/")
            type = "video/mpeg"
        }
        val imageIntent = MyIntent().apply {
            data = URI("http://pitterest.com/")
            type = "image/jpg"
        }
        val imageFilter = MyIntentFilter().apply {
            scheme = "http"
            type = "image/jpg"
        }
        val onlySchemeFilter = MyIntentFilter().apply {
            scheme = "http"
        }
        Assert.assertTrue(IntentResolution.match(imageIntent, imageFilter))
        Assert.assertFalse(IntentResolution.match(videoIntent, imageFilter))
        Assert.assertTrue(IntentResolution.match(imageIntent, onlySchemeFilter))
        Assert.assertTrue(IntentResolution.match(videoIntent, onlySchemeFilter))
    }

    @Test
    fun hostTest() {
        val youtubeIntent = MyIntent().apply {
            data = URI("http://youtube.com/video/1")
        }
        val yandexIntent = MyIntent().apply {
            data = URI("http://yandex.ru/images/2")
        }
        val youtubeFilter = MyIntentFilter().apply {
            scheme = "http"
            host = "youtube.com"
        }
        Assert.assertTrue(IntentResolution.match(youtubeIntent, youtubeFilter))
        Assert.assertFalse(IntentResolution.match(yandexIntent, youtubeFilter))
    }

    @Test
    fun pathTest() {
        val video1Intent = MyIntent().apply {
            data = URI("http://youtube.com/video/1")
            type = "video/mpeg"
        }
        val video2Intent = MyIntent().apply {
            data = URI("http://youtube.com/video/2")
            type = "video/mpeg"
        }
        val videoFilter = MyIntentFilter().apply {
            scheme = "http"
            type = "video/mpeg"
        }
        val videoWithId2Filter = MyIntentFilter().apply {
            scheme = "http"
            path = "/video/2"
            type = "video/mpeg"
        }

        Assert.assertTrue(IntentResolution.match(video1Intent, videoFilter))
        Assert.assertTrue(IntentResolution.match(video2Intent, videoFilter))
        Assert.assertFalse(IntentResolution.match(video1Intent, videoWithId2Filter))
        Assert.assertTrue(IntentResolution.match(video2Intent, videoWithId2Filter))
    }

}
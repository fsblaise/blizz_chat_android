package com.fsblaise.blizzchat.features.stories.domain.utils

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.fsblaise.blizzchat.features.stories.domain.model.Story
import kotlinx.serialization.json.Json

object StoryNavType : NavType<Story>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Story {
        return bundle.getString(key).let {
            Json.decodeFromString<Story>(it.toString())
        }
    }

    override fun parseValue(value: String): Story {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: Story): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: Story) {
        bundle.putString(key, value.let { Json.encodeToString(it) })
    }
}
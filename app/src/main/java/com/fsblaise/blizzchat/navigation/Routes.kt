package com.fsblaise.blizzchat.navigation

import com.fsblaise.blizzchat.features.stories.domain.model.Story
import kotlinx.serialization.Serializable

// Auth nested graph
@Serializable
object Auth

// Routes inside the Auth graph

@Serializable
object Welcome

@Serializable
object CheckEmail

@Serializable
object SignIn

@Serializable
object SignUp

// Home nested graph
@Serializable
object Home

// Routes inside the Home graph

@Serializable
object Chats

@Serializable
object Settings

@Serializable
object Stories

@Serializable
object ProfileDialog

@Serializable
object Profile

// Normal routes outside the navbar

@Serializable
data class Messaging (
    val chatId: String
)

// Create story routes

@Serializable
object Camera

@Serializable
data class DisplayPicture (
    val imgPath: String
)

@Serializable
data class ViewStory (
    val story: Story
)
package com.dtoanng.jetpack_compose_instagram.presentation.mainscreen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dtoanng.jetpack_compose_instagram.R
import com.dtoanng.jetpack_compose_instagram.presentation.home.Home
import com.dtoanng.jetpack_compose_instagram.presentation.profile.Profile
import com.dtoanng.jetpack_compose_instagram.presentation.reels.Reels
import com.dtoanng.jetpack_compose_instagram.presentation.search.Search
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

private enum class NavigationSection(val icon: Int, val selectedIcon: Int, val title: String) {
    Home(R.drawable.ic_home_outline, R.drawable.ic_home_fill, "Home"),
    Search(R.drawable.ic_search, R.drawable.ic_search_fill, "Search"),
    Add(R.drawable.ic_add_outline, R.drawable.ic_add_fill, "Add"),
    Reels(R.drawable.ic_reels, R.drawable.ic_reels_fill, "Reels"),
    Profile(R.drawable.ic_user, R.drawable.ic_user, "Profile")
}

@Composable
fun MainScreen() {

    val sectionState = remember {
        mutableStateOf(NavigationSection.Home)
    }

    val navItems = NavigationSection.entries.toList()

    Scaffold(
        bottomBar = {
            BottomBar(
                items = navItems,
                currentSection = sectionState.value,
                onSectionSelected = { sectionState.value = it },
            )
        },
        content = { innerPadding ->
            Crossfade(
                targetState = sectionState.value,
                modifier = Modifier.padding(innerPadding),
                label = ""
            ) { section ->
                when (section) {
                    NavigationSection.Home -> Home()
                    NavigationSection.Search -> Search()
                    NavigationSection.Add -> {
                        //todo:
                    }

                    NavigationSection.Reels -> Reels()
                    NavigationSection.Profile -> Profile()
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}

@Composable
private fun BottomBar(
    items: List<NavigationSection>,
    currentSection: NavigationSection,
    onSectionSelected: (NavigationSection) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.background)
    ) {
        items.forEach { section ->

            val selected = section == currentSection
            val iconRes = if (selected) section.selectedIcon else section.icon

            NavigationBarItem(
                selected = selected,
                alwaysShowLabel = false,
                onClick = { onSectionSelected(section) },
                icon = {
                    if (section == NavigationSection.Profile) {
                        BottomBarProfile(isSelected = selected)
                    } else {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = section.title,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(2.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                })

        }
    }
}

@Composable
private fun BottomBarProfile(isSelected: Boolean) {
    var profilePictureUrl by remember { mutableStateOf("") }
    val profilePictureShape = CircleShape

    // todo: move this code into repository
    val db = FirebaseFirestore.getInstance()
    db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
        .get().addOnCompleteListener { task: Task<DocumentSnapshot?> ->
            if (task.isSuccessful && task.result != null) {
                profilePictureUrl = task.result!!.getString("imageUrl").toString()
//                currentUser.image = profilePictureUrl
            } else {
                //deal with error
            }
        }
    Box(
        modifier = if (isSelected) {
            Modifier
                .border(
                    color = Color.Black,
                    width = 1.dp,
                    shape = profilePictureShape
                )
        } else Modifier,
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .padding(if (isSelected) 3.dp else 0.dp)
                .background(color = Color.LightGray, shape = profilePictureShape)
                .clip(profilePictureShape)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = profilePictureUrl),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}
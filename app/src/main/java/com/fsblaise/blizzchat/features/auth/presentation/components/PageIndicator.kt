package com.fsblaise.blizzchat.features.auth.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.pillStar
import androidx.graphics.shapes.toPath

@Composable
fun PageIndicator(
    index: Int,
    currentIndex: Int,
) {
    val colorScheme = colorScheme
    val morphProgress by animateFloatAsState(
        targetValue = if (currentIndex == index) 0f else 1f,
        label = "MorphProgress"
    )

    val startColor = colorScheme.primary
    val endColor = colorScheme.outlineVariant
    val morphColor by animateColorAsState(
        targetValue = lerp(startColor, endColor, morphProgress),
        label = "MorphColor"
    )

    Box(
        modifier = Modifier
            .size(24.dp)
            .drawWithCache {
                // pillStar takes in a ratio param as well
                val cookieShape = RoundedPolygon.Companion.pillStar(
                    numVerticesPerRadius = 4,
                    width = size.minDimension / 1.3f,
                    height = size.minDimension / 1.3f,
                    innerRadiusRatio = .529f, // Figma ratio
                    rounding = CornerRounding(radius = 14f),
                    innerRounding = CornerRounding(radius = 14f),
                    startLocation = 0.125f, // We can rotate with this param
                    centerX = size.width / 2,
                    centerY = size.height / 2,
                )

                val scallopedShape = RoundedPolygon.Companion.pillStar(
                    numVerticesPerRadius = 12,
                    width = size.minDimension / 2f,
                    height = size.minDimension / 2f,
                    innerRadiusRatio = .852f, // Figma ratio
                    rounding = CornerRounding(radius = 6f),
                    innerRounding = CornerRounding(radius = 6f),
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                )

                val morph = Morph(cookieShape, scallopedShape)
                val morphPath = morph
                    .toPath(progress = morphProgress).asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = morphColor)
                }
            }
    ) {

    }
}

@Preview
@Composable
fun PageIndicatorInactivePreview() {
    PageIndicator(index = 0, currentIndex = 1)
}

@Preview
@Composable
fun PageIndicatorActivePreview() {
    PageIndicator(index = 0, currentIndex = 0)
}
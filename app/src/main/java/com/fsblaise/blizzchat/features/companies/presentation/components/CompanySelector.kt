package com.fsblaise.blizzchat.features.companies.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.fsblaise.blizzchat.features.companies.domain.model.Company

@Composable
fun CompanySelector(
    companies: List<Company>,
    onDismiss: () -> Unit,
    onCompanySelected: (Company) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val colorScheme = colorScheme

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Your Company") },
        text = {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                companies.forEach { company ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = !isLoading
                            ) {
                                isLoading = true
                                onCompanySelected(company)
                                isLoading = false
                                onDismiss()
                            },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.Transparent,
                        ),
                        headlineContent = {
                            Text(
                                company.name,
                                color = if (isLoading) colorScheme.outlineVariant  else colorScheme.primary,
                            )
                        },
                        trailingContent = {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 3.dp
                                )
                            }
                        },
                        supportingContent = {
                            if (errorMessage.isNotEmpty()) {
                                Text(
                                    errorMessage,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        properties = DialogProperties(dismissOnClickOutside = false)
    )
}

@Preview
@Composable
fun CompanySelectorPreview() {
    val companies = listOf(
        Company(
            name = "Self hosted",
            apiUrl = "",
            members = emptyList()
        ),
        Company(
            name = "Self hosted 2",
            apiUrl = "",
            members = emptyList()
        ),
        Company(
            name = "Blizz Chat",
            apiUrl = "",
            members = emptyList()
        )
    )

    CompanySelector(
        companies = companies,
        onDismiss = {},
        onCompanySelected = {}
    )
}

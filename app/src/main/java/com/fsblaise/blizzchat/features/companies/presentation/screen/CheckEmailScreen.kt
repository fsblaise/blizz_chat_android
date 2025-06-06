package com.fsblaise.blizzchat.features.companies.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.BuildConfig
import com.fsblaise.blizzchat.features.companies.domain.model.Company
import com.fsblaise.blizzchat.features.companies.presentation.components.CompanySelector
import com.fsblaise.blizzchat.features.companies.presentation.state.CheckFormState
import com.fsblaise.blizzchat.features.companies.presentation.state.CheckFormViewModel
import com.fsblaise.blizzchat.features.companies.presentation.state.CompaniesState
import com.fsblaise.blizzchat.features.companies.presentation.state.CompaniesViewModel
import com.fsblaise.blizzchat.navigation.SignIn
import com.fsblaise.blizzchat.theme.BlizzChatTheme

@Composable
fun CheckEmailScreen(
    navController: NavController,
    formViewModel: CheckFormViewModel = hiltViewModel(),
    companiesViewModel: CompaniesViewModel = hiltViewModel(),
) {
    val formState = formViewModel.state.value
    val companiesState = companiesViewModel.state.collectAsState()

    CheckEmailScreen(
        navController = navController,
        formState = formState,
        companiesState = companiesState,
        onEmailChange = formViewModel::onEmailChange,
        checkIfEmailInCompany = companiesViewModel::checkIfEmailInCompany,
        selectCompany = companiesViewModel::selectCompany
    )
}

@Composable
fun CheckEmailScreen(
    navController: NavController,
    formState: CheckFormState,
    companiesState: State<CompaniesState>,
    onEmailChange: (String) -> Unit,
    checkIfEmailInCompany: (String) -> Unit,
    selectCompany: (Company, String) -> Unit,
) {

    var fetchedCompanies = (companiesState.value as? CompaniesState.Fetched)?.companies.orEmpty()
    val colorScheme = colorScheme
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(companiesState.value) {
        if (companiesState.value is CompaniesState.Fetched) {
            showDialog = true
        }
        if (companiesState.value is CompaniesState.Selected) {
            navController.navigate(SignIn)
        }
        if (companiesState.value is CompaniesState.Error) {
            val errorMessage = (companiesState.value as CompaniesState.Error).message
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Verification",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Please enter your email address",
                        fontSize = 18.sp,
                    )
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = formState.email,
                        onValueChange = { onEmailChange(it) },
                        label = { Text("Email") },
                        singleLine = true,
                        isError = formState.emailError != null,
                        supportingText = if (formState.emailError != null) {
                            { Text(text = formState.emailError, color = colorScheme.error) }
                        } else null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            isLoading = true
                            checkIfEmailInCompany(formState.email)
//                        navController.navigate(Login)
                        },
                        enabled = formState.isValid && !isLoading,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (isLoading)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(
                                    color = colorScheme.outlineVariant,
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 3.dp
                                )
                                Spacer(Modifier.width(8.dp))
                                Text("Check Email")
                            }
                        else
                            Text("Check Email")
                    }
                }
            }
            if (showDialog) {
                val companies = fetchedCompanies + Company(
                    name = "Blizz Chat",
                    apiUrl = BuildConfig.API_URL,
                    members = emptyList()
                )
                CompanySelector(
                    companies = companies,
                    onDismiss = {
                        showDialog = false
                        isLoading = false
                    },
                    onCompanySelected = { company ->
                        selectCompany(company, formState.email)
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CheckEmailPreview() {
    val mockNavController = rememberNavController()
    val mockFormState = CheckFormState(
        email = "test@email.com",
        isValid = true,
        emailError = null
    )
    val mockCompaniesState: State<CompaniesState> = remember {
        mutableStateOf(
            CompaniesState.Fetched(
                companies = listOf(
                    Company(
                        name = "Company A",
                        apiUrl = "https://companya.com/",
                        members = emptyList()
                    ),
                    Company(
                        name = "Company B",
                        apiUrl = "http://localhost:1234/",
                        members = emptyList()
                    ),
                )
            )
        )
    }

    BlizzChatTheme {
        CheckEmailScreen(
            navController = mockNavController,
            formState = mockFormState,
            onEmailChange = {},
            companiesState = mockCompaniesState,
            checkIfEmailInCompany = {},
            selectCompany = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckEmailPreviewForm() {
    val mockNavController = rememberNavController()
    val mockFormState = CheckFormState(
        email = "asd",
        isValid = false,
        emailError = "Invalid email format"
    )
    val mockCompaniesState: State<CompaniesState> = remember {
        mutableStateOf(
            CompaniesState.Initial
        )
    }

    BlizzChatTheme {
        CheckEmailScreen(
            navController = mockNavController,
            formState = mockFormState,
            onEmailChange = {},
            companiesState = mockCompaniesState,
            checkIfEmailInCompany = {},
            selectCompany = { _, _ -> }
        )
    }
}
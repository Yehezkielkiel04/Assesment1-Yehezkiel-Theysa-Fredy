@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = {
                    input = it
                    error = it.toFloatOrNull() == null
                },
                label = { Text(stringResource(id = R.string.input_temp)) },
                isError = error,
                supportingText = {
                    if (error) Text(text = stringResource(id = R.string.invalid_input))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

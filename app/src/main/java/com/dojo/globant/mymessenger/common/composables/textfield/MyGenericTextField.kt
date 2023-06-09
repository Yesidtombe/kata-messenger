package com.dojo.globant.mymessenger.common.composables.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.util.UiText
import com.dojo.globant.mymessenger.ui.theme.Red
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun MyGenericTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    errorMessage: UiText? = null,
    @StringRes label: Int? = null,
    @StringRes placeholder: Int? = null,
    startIcon: ImageVector? = null,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier.fillMaxWidth()) {
        MyOutlinedTextField(
            text = text,
            onValueChange = onValueChange,
            isError = errorMessage != null,
            label = label,
            placeholder = placeholder,
            startIcon = startIcon,
            isEnabled = isEnabled,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation
        )

        errorMessage?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                text = it.asString(),
                style = Typography.labelSmall,
                color = Red
            )
        }
    }
}

@Composable
fun MyOutlinedTextField(
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    @StringRes label: Int?,
    @StringRes placeholder: Int?,
    startIcon: ImageVector?,
    isEnabled: Boolean,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        isError = isError,
        label = {
            label?.let {
                Text(text = stringResource(id = it))
            }
        },
        placeholder = {
            placeholder?.let {
                Text(text = stringResource(id = it))
            }
        },
        enabled = isEnabled,
        leadingIcon = if (startIcon != null) {
            { Icon(imageVector = startIcon, contentDescription = null) }
        } else null,
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines
    )
}

@Preview(name = "MyGenericTextField", showBackground = true)
@Composable
fun MyGenericTextFieldPreview() {
    MyGenericTextField(
        text = "",
        label = R.string.app_name,
        placeholder = R.string.app_name,
        onValueChange = { },
        startIcon = Icons.Default.Add
    )
}
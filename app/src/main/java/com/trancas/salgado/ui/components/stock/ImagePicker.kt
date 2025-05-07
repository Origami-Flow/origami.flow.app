package com.trancas.salgado.ui.components.stock

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.trancas.salgado.R

@Composable
fun ImagePicker(selectedImageUri: Uri?, onImageSelected: (Uri) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageSelected(it)
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clickable { launcher.launch("image/*") }
    ) {
        if (selectedImageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Imagem selecionada",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painterResource(id = R.drawable.add_image),
                stringResource(id = R.string.image_description, "produto"),
                Modifier
                    .size(45.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

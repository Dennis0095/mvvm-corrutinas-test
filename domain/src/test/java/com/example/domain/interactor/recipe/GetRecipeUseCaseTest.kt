package com.example.domain.interactor.recipe

import com.example.domain.core.RequestResult
import com.example.domain.model.RecipeHome
import com.example.domain.repository.recipe.GetRecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetRecipeUseCaseTest{

    //@MockK
    @RelaxedMockK
    private lateinit var repo: GetRecipeRepository//Mockeamos el repositorio quien nos trae toda la información

    lateinit var getRecipeUseCase: GetRecipeUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this)// Iniciamos el mock
        getRecipeUseCase = GetRecipeUseCase(repo)
    }

    @Test
    fun obtenerResultadoDeRecetas() = runBlocking{

        val list = listOf(RecipeHome())
        coEvery { repo.getRecipes() } returns RequestResult.Success(list)

        getRecipeUseCase.getRecipes()

        coVerify (exactly = 1){ repo.getRecipes() }
    }

    @Test
    fun cuandoOcurreUnErrorAlTraerLasRecetasDelServicio() = runBlocking{
        coEvery { repo.getRecipes() } returns RequestResult.Error(IOException())

        getRecipeUseCase.getRecipes()

        coVerify (exactly = 1){ repo.getRecipes() }
    }

}
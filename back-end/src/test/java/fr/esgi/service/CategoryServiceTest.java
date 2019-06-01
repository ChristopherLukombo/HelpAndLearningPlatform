package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.esgi.dao.CategoryRepository;
import fr.esgi.service.dto.CategoryDTO;
import fr.esgi.service.impl.CategoryServiceImpl;
import fr.esgi.service.mapper.CategoryMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

	private static final long ID = 1L;

	private static final String WORDING = "Informatiques";

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryMapper categoryMapper;

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	private static CategoryDTO getCategoryDTO() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(ID);
		categoryDTO.setWording(WORDING);
		return categoryDTO;
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsOK() {
		// Given
		CategoryDTO categoryDTO = getCategoryDTO();

		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categoriesDTO.add(categoryDTO);

		// When
		final List<CategoryDTO> categories = categoryServiceImpl.findCategoriesByWording(anyString());
		when(categories).thenReturn(categoriesDTO);

		// Then
		assertThat(categories).isNotNull();
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsEmpty() {
		// Given
		List<CategoryDTO> categoriesDTO = new ArrayList<>();

		// When
		when(categoryServiceImpl.findCategoriesByWording(anyString())).thenReturn(categoriesDTO);

		// Then
		assertThat(categoryServiceImpl.findCategoriesByWording(anyString())).isEmpty();
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsKO() {
		// Given
		List<CategoryDTO> categoriesDTO = null;

		// When
		when(categoryServiceImpl.findCategoriesByWording(anyString())).thenReturn(categoriesDTO);

		// Then
		assertThatThrownBy(() -> categoryServiceImpl.findCategoriesByWording(anyString()))
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldFindAllCategoriesWhenIsOK() {
		// Given
		CategoryDTO categoryDTO = getCategoryDTO();

		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categoriesDTO.add(categoryDTO);

		// When
		final List<CategoryDTO> categories = categoryServiceImpl.findAll();
		when(categories).thenReturn(categoriesDTO);

		// Then
		assertThat(categories).isNotNull();
	}

	@Test
	public void shouldFindAllCategoriesWhenIsEmpty() {
		// Given
		CategoryDTO categoryDTO = getCategoryDTO();

		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categoriesDTO.add(categoryDTO);

		// When
		final List<CategoryDTO> categories = categoryServiceImpl.findAll();
		when(categories).thenReturn(categoriesDTO);

		// Then
		assertThat(categories).isEmpty();
	}

	@Test
	public void shouldFindAllWhenIsKO() {
		// Given
		List<CategoryDTO> categoriesDTO = null;

		// When
		when(categoryServiceImpl.findAll()).thenReturn(categoriesDTO);

		// Then
		assertThatThrownBy(() -> categoryServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}
}

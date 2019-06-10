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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import fr.esgi.dao.CategoryRepository;
import fr.esgi.domain.Category;
import fr.esgi.service.impl.CategoryServiceImpl;
import fr.esgi.service.mapper.CategoryMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
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
		categoryServiceImpl = new CategoryServiceImpl(categoryRepository, categoryMapper);
	}

	private static Category getCategory() {
		Category category = new Category();
		category.setId(ID);
		category.setWording(WORDING);
		return category;
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsOK() {
		// Given
		List<Category> categorys = new ArrayList<>();
		categorys.add(getCategory());

		// When
		when(categoryRepository.findCategoriesByWording(anyString())).thenReturn(categorys);

		// Then
		assertThat(categoryServiceImpl.findCategoriesByWording(anyString())).isNotNull();
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsEmpty() {
		// Given
		List<Category> categorys = new ArrayList<>();

		// When
		when(categoryRepository.findCategoriesByWording(anyString())).thenReturn(categorys);

		// Then
		assertThat(categoryServiceImpl.findCategoriesByWording(anyString())).isEmpty();
	}

	@Test
	public void shouldFindCategoriesByWordingWhenIsKO() {
		// Given
		List<Category> categorys = null;

		// When
		when(categoryRepository.findCategoriesByWording(anyString())).thenReturn(categorys);

		// Then
		assertThatThrownBy(() -> categoryServiceImpl.findCategoriesByWording(anyString()))
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldFindAllCategoriesWhenIsOK() {
		// Given
		List<Category> categorys = new ArrayList<>();
		categorys.add(getCategory());

		// When
		when(categoryRepository.findAll()).thenReturn(categorys);

		// Then
		assertThat(categoryServiceImpl.findAll()).isNotEmpty();
	}

	@Test
	public void shouldFindAllCategoriesWhenIsEmpty() {
		// Given
		List<Category> categorys = new ArrayList<>();

		// When
		when(categoryRepository.findAll()).thenReturn(categorys);

		// Then
		assertThat(categoryServiceImpl.findAll()).isEmpty();
	}

	@Test
	public void shouldFindAllWhenIsKO() {
		// Given
		List<Category> categorys = null;

		// When
		when(categoryRepository.findAll()).thenReturn(categorys);

		// Then
		assertThatThrownBy(() -> categoryServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}
}

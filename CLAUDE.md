# Hoarder Scrapbook

Android app helping people with hoarding tendencies preserve memories of items they discard. Users photograph items, add descriptions, and view them later in a slideshow.

## Build & Run

- **Language**: Kotlin, Groovy Gradle files
- **Build**: `./gradlew assembleDebug`
- **Test**: `./gradlew testDebugUnitTest`
- **Min SDK**: 24, **Target/Compile SDK**: 34

## Architecture

- **UI**: Jetpack Compose with MVI pattern (BaseViewModel)
- **DI**: Dagger Hilt
- **DB**: Room (entities in `models/`, DAOs in `storage/room_db/`)
- **Navigation**: Jetpack Navigation Compose (`ui/navigation/`)
- **Image loading**: Coil

## Screen Pattern (MVI)

Each screen lives in `ui/views/<screen_name>/` with 4 files:

### Contract — defines Event, State, Effect

```kotlin
class ExampleContract {
    sealed class Event : ViewEvent {
        data object OnBack : Event()
    }
    data class State(val items: List<Item>) : ViewState
    sealed class Effect : ViewEffect {
        data object ToPreviousScreen : Effect()
    }
}
```

### ViewModel — extends BaseViewModel<Event, State, Effect>

```kotlin
@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repo: MemoryRepository
) : BaseViewModel<ExampleContract.Event, ExampleContract.State, ExampleContract.Effect>() {
    override fun setInitialState() = ExampleContract.State(items = emptyList())
    override fun handleEvents(event: ExampleContract.Event) {
        when (event) {
            ExampleContract.Event.OnBack -> setEffect(ExampleContract.Effect.ToPreviousScreen)
        }
    }
}
```

### Screen — stateless @Composable, receives State + onEvent lambda

```kotlin
@Composable
fun ExampleScreen(
    viewState: State<ExampleContract.State>,
    onEvent: (ExampleContract.Event) -> Unit
) {
    val state = viewState.value
    // UI here
}
```

### Destination — wires ViewModel to Screen, handles navigation effects

```kotlin
@Composable
fun ExampleDestination(
    navController: NavController,
    viewModel: ExampleViewModel = hiltViewModel()
) {
    ExampleScreen(viewState = viewModel.viewState, onEvent = { viewModel.setEvent(it) })
    LaunchedEffect(VIEW_EFFECTS_KEY) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                ExampleContract.Effect.ToPreviousScreen -> navController.popBackStack()
            }
        }.collect()
    }
}
```

### If the ViewModel needs assisted injection (e.g. runtime params)

Add a `ViewModelFactory` interface:

```kotlin
@AssistedFactory
interface ExampleViewModelFactory {
    fun create(repo: MemoryRepository): ExampleViewModel
}
```

## Key Models

- `Memory` — Room entity (`memories` table): id, entryDate, estimateDateOfMemory, name, description
- `ImageUri` — Room entity (`image_uris` table): id, memoryId, uri
- `MemoryWithImages` — Room relation class combining Memory + List<ImageUri>
- `MemoryDataWrapper` — in-memory state for passing selected memory ID between screens

## Room Database

- Database class: `MemoryRoomDatabase` (version 2, destructive migration)
- DAOs: `MemoryDao`, `ImageUriDao`
- Use `@Transaction` + `@Embedded`/`@Relation` for queries returning `MemoryWithImages`
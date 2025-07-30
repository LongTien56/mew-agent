# Mew Agent - AI-Powered Web Automation Assistant

A powerful JavaFX application that acts as an intelligent AI agent capable of automating web tasks across multiple websites. The agent learns from each interaction and builds a knowledge base for future use.

## 🚀 Key Features

### Multi-Website Automation
- **Universal Compatibility**: Works with any website without being bound to specific platforms
- **Adaptive Learning**: Learns website patterns and improves over time
- **Smart Fallbacks**: Uses cached patterns when primary automation fails

### AI-Powered Task Execution
- **Natural Language Commands**: Execute tasks using simple English commands
- **LLM Integration**: Supports OpenAI GPT and Google Gemini APIs
- **Dynamic Strategy Generation**: AI creates automation plans based on current page content

### Intelligent Caching & Learning
- **Local Database**: SQLite database stores learned patterns and task history
- **Pattern Recognition**: Automatically identifies and saves successful automation workflows
- **Success Rate Tracking**: Monitors and improves automation reliability

### Supported Task Types
- 🛒 **E-commerce**: Automated purchasing from different online stores
- 📋 **Task Management**: Update progress on Trello, Asana, Jira, etc.
- 📧 **Communication**: Email automation, social media posting
- 📊 **Data Entry**: Form filling, spreadsheet updates
- 🔍 **Information Gathering**: Web scraping, price monitoring

## 🏗️ Architecture & Design Patterns

The application follows modern software engineering best practices:

### 🔧 Design Patterns Used

- **Dependency Injection**: Loose coupling between components
- **Repository Pattern**: Clean data access layer abstraction
- **Data Transfer Objects (DTOs)**: Clean data contracts between layers
- **Service Layer Pattern**: Business logic separation
- **Strategy Pattern**: Multiple LLM providers support
- **Factory Pattern**: Browser instance creation

### 📋 Layer Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │   JavaFX UI     │    │  Controllers    │                │
│  │   (FXML Views)  │◄──►│ (MainController)│                │
│  └─────────────────┘    └─────────────────┘                │
└─────────────────────────────┬───────────────────────────────┘
                              │ Dependency Injection
┌─────────────────────────────▼───────────────────────────────┐
│                     Service Layer                           │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐
│  │  AgentService   │  │TaskExecutionSvc │  │  LLMService     │
│  │ (IAgentService) │  │(ITaskExecSvc)   │  │  BrowserService │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘
└─────────────────────────────┬───────────────────────────────┘
                              │ Repository Interfaces
┌─────────────────────────────▼───────────────────────────────┐
│                  Data Access Layer                          │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │  TaskRepository │    │WebsitePattern   │                │
│  │   (Interface)   │    │  Repository     │                │
│  │      ▲          │    │  (Interface)    │                │
│  │ SQLiteTaskRepo  │    │SQLitePatternRepo│                │
│  └─────────────────┘    └─────────────────┘                │
└─────────────────────────────┬───────────────────────────────┘
                              │ DTOs
┌─────────────────────────────▼───────────────────────────────┐
│                     Data Layer                              │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐
│  │    TaskDTO      │  │WebsitePatternDTO│  │WebAutomationDTO│
│  │                 │  │                 │  │     PlanDTO     │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘
└─────────────────────────────────────────────────────────────┘
```

### 🧩 Key Components

#### Dependency Injection Container
- **DIContainer**: Manages object lifecycle and dependencies
- **Constructor Injection**: Services receive dependencies via constructor
- **Interface-based Design**: Easy mocking and testing

#### Repository Pattern
- **TaskRepository**: Abstract data access for tasks
- **WebsitePatternRepository**: Abstract data access for learned patterns
- **SQLite Implementations**: Concrete database implementations

#### Data Transfer Objects
- **TaskDTO**: Clean task data representation
- **WebsitePatternDTO**: Website automation patterns
- **WebAutomationPlanDTO**: AI-generated automation plans

## 🎯 Example Commands

### Shopping Automation
```
buy wireless headphones from amazon
purchase laptop from best buy
buy coffee beans from starbucks
```

### Task Management
```
update task progress on trello
update project status on asana
mark task complete on jira
```

### General Automation
```
automate login to my email
automate data entry on form
automate price check for product
```

### Learning Commands
```
learn website amazon
learn website trello
```

## 🛠️ Setup & Configuration

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher
- Chrome or Firefox browser
- OpenAI API key or Google Gemini API key

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd mew-agent
   ```

2. **Configure API Keys**
   Edit `src/main/resources/application.properties`:
   ```properties
   openai.api.key=your-actual-openai-api-key
   gemini.api.key=your-actual-gemini-api-key
   ```

3. **Install WebDriver**
   - For Chrome: Download ChromeDriver and add to PATH
   - For Firefox: Download GeckoDriver and add to PATH

4. **Build the project**
   ```bash
   mvn clean compile
   ```

5. **Run tests**
   ```bash
   mvn test
   ```

6. **Start the application**
   ```bash
   mvn javafx:run
   ```

## 📊 How It Works

### 1. Command Processing
When you enter a command like "buy laptop from amazon":
- The AgentService parses the command type and parameters
- Extracts the item ("laptop") and target website ("amazon")
- Creates a Task object with relevant metadata

### 2. AI Planning
- The LLMService sends the task description and target website to the AI model
- AI analyzes the current page structure and generates a step-by-step automation plan
- Returns CSS selectors and action sequences

### 3. Execution & Learning
- BrowserService opens the target website
- TaskExecutionService executes each step of the AI-generated plan
- Success/failure patterns are saved to the local database
- Future executions use learned patterns for improved reliability

### 4. Adaptive Improvement
- Each successful automation creates a WebsitePattern entry
- Failed attempts trigger fallback to previously successful patterns
- Success rates are tracked and used to prioritize automation strategies

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── module-info.java              # Java module definition
│   │   └── com/example/mewagent/
│   │       ├── MainApp.java              # Main application class
│   │       ├── controller/
│   │       │   └── MainController.java   # UI controller
│   │       └── service/
│   │           └── AgentService.java     # Business logic service
│   └── resources/
│       ├── com/example/mewagent/
│       │   └── MainView.fxml             # UI layout
│       ├── application.properties        # Configuration
│       └── logback.xml                   # Logging configuration
└── test/
    └── java/
        └── com/example/mewagent/
            ├── AppTest.java              # Application tests
            └── controller/
                └── MainControllerTest.java # Controller tests
```

## Architecture

The application follows modern Java best practices:

### Separation of Concerns
- **Controller Layer**: Handles UI interactions (`MainController`)
- **Service Layer**: Contains business logic (`AgentService`)
- **View Layer**: FXML files for UI layout

### Modern Java Features
- Java 21 with modern syntax (switch expressions, text blocks)
- Virtual threads for async operations
- Module system with `module-info.java`

### Testing
- JUnit 5 for unit testing
- TestFX for JavaFX UI testing
- Mockito for mocking dependencies

### Logging
- SLF4J API with Logback implementation
- Configurable log levels
- File and console appenders

## Available Commands

When running the application, you can use these commands:

- `hello` - Greet the agent
- `help` - Show available commands
- `status` - Check system status
- `version` - Show application version
- `clear` - Clear the log area
- `exit` - Close the application

## Development

### Code Style
- Follow Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public APIs
- Keep methods focused and single-purpose

### Adding New Features
1. Add business logic to appropriate service classes
2. Update controllers only for UI-related logic
3. Write tests for new functionality
4. Update documentation

## Dependencies

### Runtime Dependencies
- **JavaFX**: Modern UI framework
- **Selenium**: Web automation capabilities
- **SQLite**: Local database storage
- **Gson**: JSON processing
- **Apache HttpClient**: HTTP communication
- **SLF4J + Logback**: Logging framework

### Test Dependencies
- **JUnit 5**: Testing framework
- **TestFX**: JavaFX testing
- **Mockito**: Mocking framework

## Build Plugins

- **Maven Compiler Plugin**: Compiles Java source code
- **Maven Surefire Plugin**: Runs unit tests
- **JavaFX Maven Plugin**: Runs JavaFX applications
- **Maven Shade Plugin**: Creates fat JARs with dependencies

## License

This project is licensed under the MIT License.

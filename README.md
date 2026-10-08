# 电子照片管理系统

一个基于 **JavaFX / FXML** 的桌面图片管理实验项目，提供文件树浏览、图片预览、文件操作、图片显示调整和幻灯片界面。

## 功能

根据 `src/action/`、控制器和模型代码，项目包含：

- 文件树导航与图片打开。
- 复制、剪切、粘贴、删除、重命名。
- 放大、缩小、旋转、复位，以及上一张/下一张。
- 图片排序、模糊搜索、属性查看。
- 幻灯片播放界面。

旋转等显示操作是否保存回文件，应以具体实现为准；不要把预览变换等同于持久化图片编辑。

## 技术栈与目录

Java、JavaFX、FXML；仓库保留 IntelliJ IDEA `.iml` 文件，没有 Maven/Gradle 构建脚本。

```text
src/
├── application/Main.java   主类 application.Main
├── controller/             FXML 控制器
├── model/                  图片、文件树、菜单和提示模型
├── action/                 文件操作与图片显示操作
├── service/                界面和鼠标事件服务
├── view/                   MainUI.fxml、ViewUI2.fxml、PPT.fxml
└── META-INF/MANIFEST.MF    清单文件
```

## 环境要求

- JDK 与匹配的 JavaFX 库；部分 JDK 8 发行版附带 JavaFX，其他环境需要独立配置。
- IntelliJ IDEA 或其他支持 classpath 配置的 IDE。
- 本机可读写的图片目录。

`.iml` 引用了名为 `javafx-swt` 的项目库，但仓库没有包含对应 SDK/JAR，不能只靠这个文件恢复全部环境。

## 启动步骤

```bash
git clone https://github.com/RRRRUA/Electronic-Image-Management-Program.git
cd Electronic-Image-Management-Program
```

1. 用 IDE 打开项目并设置 JDK。
2. 将 `src/` 标记为源码根目录，加入 JavaFX 依赖。
3. 确保 FXML 文件复制到构建输出的 `view/`，主类通过 `/view/MainUI.fxml` 加载界面。
4. 创建 Java Application 运行配置，主类设为 `application.Main`。
5. 在测试图片目录中体验文件树和图片操作。

在需要独立 JavaFX SDK 的 JDK 环境中，可以按本机 SDK 路径配置 VM options：

```text
--module-path "<JavaFX SDK 的 lib 目录>" --add-modules javafx.controls,javafx.fxml
```

这组参数是环境配置示例，前提是使用兼容的 JDK/JavaFX SDK，并已编译项目。仓库当前没有可直接下载运行的发行包或统一构建命令。

## 使用建议

从复制出来的测试图片开始，验证预览、排序和幻灯片，再测试文件操作。剪切、删除和重命名针对本地文件，不仅影响界面显示。

## 常见问题

| 现象 | 检查方向 |
| --- | --- |
| 编译器找不到 `javafx.*` | 确认 SDK、库路径与 JDK 版本 |
| 启动时找不到 FXML | 检查 `view/` 资源是否出现在 classpath，大小写是否一致 |
| 文件操作失败 | 检查权限、占用状态和目标目录 |
| 图片无法预览 | 检查文件格式、损坏情况与 JavaFX 支持情况 |

## 验证与项目状态

本次静态核对了入口、FXML 路径、操作类和项目文件；当前机器未配置 JavaFX SDK，未完成 GUI 运行验证。支持的图片格式、跨平台行为与发行环境尚未形成完整测试记录。

## 贡献与许可证

建议通过 PR 补充统一构建脚本、依赖版本和演示截图。仓库当前没有根目录许可证文件，复用或分发前请确认授权。

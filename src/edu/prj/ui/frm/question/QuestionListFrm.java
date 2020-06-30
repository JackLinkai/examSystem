package edu.prj.ui.frm.question;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.QuestionBean;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.QuestionServiceImpl;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class QuestionListFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 无参构造方法
	 */
	public QuestionListFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	private JLabel lblTitle;// 管理界面的标题
	private JLabel lblQTypeSearch;// 题目类型搜索标签
	private JTextField txtQTypeSearch;// 题目类型搜索文本框
	private JLabel lblSubjectName;// 科目名称搜索标签
	private JTextField txtSubjectName;// 科目名称搜索文本框

	private JButton btnSearch;// 查询按钮

	private JButton btnReset;// 重置按钮
	private JButton btnInsert;// 添加按钮
	private JLabel lblMsg; // 提示信息的标签

	/*
	 * 1.初始化用户界面
	 */

	private void initUI() {
		// 定义当前窗体的宽高
		int width = 1000;
		int height = 800;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("题库管理");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);
		/* 添加管理界面标题 */
		lblTitle = new JLabel();
		lblTitle.setText("题库信息列表");
		lblTitle.setBounds(385, 5, 300, 80);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 35));
		container.add(lblTitle);

		lblMsg = new JLabel();
		lblMsg.setText("注意：题目类型搜索采用1,2,3（1代表判断题，2代表单选题，3代表多选题）");
		lblMsg.setBounds(30, 510, 800, 50);
		lblMsg.setFont(new Font("宋体", Font.BOLD, 15));
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

		// 添加姓名搜索的标签
		lblQTypeSearch = new JLabel();
		lblQTypeSearch.setText("题目类型：");
		lblQTypeSearch.setBounds(320, 580, 70, 30);
		container.add(lblQTypeSearch);

		// 添加姓名搜索的文本框
		txtQTypeSearch = new JTextField();
		txtQTypeSearch.setBounds(400, 580, 180, 30);
		container.add(txtQTypeSearch);

		// 添加书名搜索的标签
		lblSubjectName = new JLabel();
		lblSubjectName.setText("科目名称：");
		lblSubjectName.setBounds(320, 630, 70, 30);
		container.add(lblSubjectName);

		// 添加书名搜索的文本框
		txtSubjectName = new JTextField();
		txtSubjectName.setBounds(400, 630, 180, 30);
		container.add(txtSubjectName);

		// 添加搜索按钮
		btnSearch = new JButton();
		btnSearch.setText("查询");
		btnSearch.setBounds(340, 680, 70, 30);
		container.add(btnSearch);

		// 添加重置按钮
		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(420, 680, 70, 30);
		container.add(btnReset);

		// 添加添加按钮
		btnInsert = new JButton();
		btnInsert.setText("添加");
		btnInsert.setBounds(500, 680, 70, 30);
		container.add(btnInsert);

		// 【Swing表格步骤4】：initUI()中初始化表格的方法
		initTableUI();

		// 【右键菜单--步骤5 】initUI()中初始化表格对象的右键菜单
		createTblObjMenu();
	}
	private void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 查询按钮事件处理
		btnSearch.addActionListener(e -> {
			btnSearch_click(e);
		});

		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);

		});

		// 添加按钮事件处理
		btnInsert.addActionListener(e -> {
			btnInsert_click(e);
		});
		// 【右键菜单--步骤6】bindEvent(),在表格对象tblObj的鼠标事件中,右击时,显示右键菜单
		tblObj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 判定击中的是鼠标的右键
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 获取鼠标所在的位置对应的表格的行号
					int focusedRowIndex = tblObj.rowAtPoint(e.getPoint());
					// 如果行号为-1，则说明鼠标位置不在数据行
					if (focusedRowIndex == -1) {
						return;
					}
					// 如果鼠标位置在数据行上。则选中该行
					tblObj.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
					// 调用相关方法，显示右键菜单
					tblObjMenu.show(tblObj, e.getX(), e.getY());
				}
			}

		});
	}

	// 添加查询功能
	private void btnInsert_click(ActionEvent e) {
		// TODO Auto-generated method stub
		QuestionInsertFrm questionInsertFrm = new QuestionInsertFrm();
		questionInsertFrm.listFrm = this;
		questionInsertFrm.setVisible(true);
		this.setVisible(false);

	}

	// 重置查询功能
	public void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtQTypeSearch.setText("");
		txtSubjectName.setText("");
		showListData();

	}

	// 根据文本框的内容搜索对应的数据
	public void btnSearch_click(ActionEvent e) {
		// TODO Auto-generated method stub
		showListData();
	}

	// 定义表格对象的右键菜单
	private JPopupMenu tblObjMenu = null;

	// 创建表格对象的右键菜单，并绑定事件
	public void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();
		// 菜单项删除
		JMenuItem deleteMenuItem = new JMenuItem("删除");
		tblObjMenu.add(deleteMenuItem);

		// 菜单项删除添加点击事件
		deleteMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteMenuItem_click(e);
			}

		});

		// 菜单项修改
		JMenuItem updateMenuItem = new JMenuItem("修改");
		tblObjMenu.add(updateMenuItem);

		// 菜单项修改添加点击事件
		updateMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateMenuItem_click(e);
			}

		});

	}

	// 删除的事件处理
	private void deleteMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		// 如果下标不为-1，则选中行为数据行
		if (index != -1) {
			// 取得表格对象的数据模型
			TableModel model = tblObj.getModel();
			// 根据选中的行和列，获取对应的数据值
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf("" + obj);

			// 创建确定删除的弹窗
			String titel = "系统提示";
			String message = "请确定是否删除选中的数据";
			int option = JOptionPane.YES_NO_OPTION;

			// 保存用户点击的按钮类型的值
			int buttonValue = JOptionPane.showConfirmDialog(null, message, titel, option);

			// 如果点击的按钮为YES，进行删除数据
			if (buttonValue == JOptionPane.YES_OPTION) {
				QuestionService questionService = new QuestionServiceImpl();
				Long result = questionService.delete(pk);
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "删除成功");
					showListData();
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		}

	}

	// 修改的事件处理
	private void updateMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取当前选中行的下标
		int index = tblObj.getSelectedRow();
		// 如果下标不为-1,则选中行为数据行
		if (index != -1) {
			TableModel model = tblObj.getModel();
			// 取得表格对象的数据模型
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf("" + obj);
			// 如果主键合法,则在修改的窗体中显示要修改的数据
			if (pk > 0) {
				QuestionUpdateFrm questionUpdateFrm = new QuestionUpdateFrm();
				// 将当前要修改的主键的值,传递到修改窗体
				questionUpdateFrm.pk = pk;
				// 修改窗体,根据传入的主键值,加载数据到各个组件中
				questionUpdateFrm.loadData();
				// 当前列表窗体的引用,传递给修改窗体,以便修改窗体关闭时,可以显示列表窗体
				questionUpdateFrm.questionListFrm = this;
				// 当前列表窗体隐藏
				this.setVisible(false);

				questionUpdateFrm.setVisible(true);

			}
		}

	}

	/**
	 * 自定义加载：在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {

	}

	/**
	 * 【Swing表格步骤1】： pnlTablePane表格面板，用来放真正的表格对象
	 */

	private JScrollPane pnlTablePane;

	/**
	 * 【Swing表格步骤2】：tblObj是表格对象
	 */
	private JTable tblObj;

	/**
	 * 【Swing表格步骤3】：初始化表格相关对象，要调用获取表格数据的方法showListDate().
	 * <p>
	 * * 【Swing表格步骤4】：initUI()中初始化表格的方法
	 */
	private void initTableUI() {
		// 初始化表格对象
		tblObj = new JTable();
		// 初始化表格面板，并放入表格对象
		pnlTablePane = new JScrollPane(tblObj);
		// 设置表格面板的位置
		pnlTablePane.setBounds(10, 100, 980, 400);
		// 整个窗体的容器中添加表格面板
		container.add(pnlTablePane);
		// 调用方法在表格对象中显示List集合的数据
		showListData();
	}

	/**
	 * 【Swing表格步骤5】：调用方法在表格对象中显示List集合的数据
	 */
	private void showListData() {
		// 5.1初始化服务对象（可以作为属性）
		QuestionService questionService = new QuestionServiceImpl();
		// 5.2从DB中提取List集合数据
		List<QuestionBean> list = null;

		String qType = txtQTypeSearch.getText().trim();
		String subjectName = txtSubjectName.getText().trim();
		
		if ((qType != null && !qType.isEmpty()) || (subjectName != null && !subjectName.isEmpty())) {
			list = questionService.listByName(subjectName, qType);
		} else {
			list = questionService.list();
		}
//		if ((qType != null)) {
//			list = questionService.listByQType(qType);
//		} else {
//			list = questionService.list();
//		}
		// 5.4将List集合数据传给【所需表格模型】【新方案】
		// 5.4.1 声明表格模型
		ATableModel<QuestionBean> tableModel = null;
		// 5.4.2 实例化表格模型，传入List集合对象和表格对象要显示的数据
		tableModel = new ATableModel<QuestionBean>(list, 12) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 24903144265467267L;

			/**
			 * 5.4.3 重写getTitle()--返回【界面表格】各列的表头的名称，传入列的下标，返回对应的下表列的名称
			 */
			@Override
			public String getTitle(int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return "题库Id";
				} else if (columnIndex == 1) {
					return "题目类型";
				} else if (columnIndex == 2) {
					return "题目";
				} else if (columnIndex == 3) {
					return "选项 A";
				} else if (columnIndex == 4) {
					return "选项 B";
				} else if (columnIndex == 5) {
					return "选项 C";
				} else if (columnIndex == 6) {
					return "选项 D";
				} else if (columnIndex == 7) {
					return "选项 E";
				} else if (columnIndex == 8) {
					return "选项 F";
				} else if (columnIndex == 9) {
					return "答案 ";
				} else if (columnIndex == 10) {
					return "所属科目";
				} else if (columnIndex == 11) {
					return "标签";
				}
				return "无";
			}

			/**
			 * 5.4.4 重写getCellValue()--返回【界面表格】各列的值，传入实体和列号，从【实体类集合】获取对应的值并返回
			 */
			@Override
			public Object getPropValue(QuestionBean bean, int columnIndex) {
				// TODO Auto-generated method stub
				// 具体显示那个字段用户自定义,如果不存在返回null
				if (columnIndex == 0) {
					return bean.getQuestionID();
				} else if (columnIndex == 1) {
					if (bean.getQType() == 1) {
						return "判断题";
					}
					if (bean.getQType() == 2) {
						return "单选题";
					}
					if (bean.getQType() == 3) {
						return "多选题";
					}
				} else if (columnIndex == 2) {
					return bean.getQuestion();
				} else if (columnIndex == 3) {
					return bean.getItemA();
				} else if (columnIndex == 4) {
					return bean.getItemB();
				} else if (columnIndex == 5) {
					return bean.getItemC();
				} else if (columnIndex == 6) {
					return bean.getItemD();
				} else if (columnIndex == 7) {
					return bean.getItemE();
				} else if (columnIndex == 8) {
					return bean.getItemF();
				} else if (columnIndex == 9) {
					return bean.getAnswer();
				} else if (columnIndex == 10) {
					return bean.getSubjectName();
				} else if (columnIndex == 11) {
					return bean.getTag();
				}
				return null;
			}

		};
		// 5.5设置表格对象的数据类型
		// 相当于，将表格模型tableModel 封装的列表数据list，显示到表格对象tblObj
		tblObj.setModel(tableModel);
	}
}

//
//  JSONDecodable.swift
//  GitHubSearchRepository
//
//  Created by SHIKI TAKAHASHI on 2017/11/09.
//  Copyright © 2017年 SHIKI TAKAHASHI. All rights reserved.
//

import Foundation

protocol JSONDecodable {
    init(json: Any) throws
}
